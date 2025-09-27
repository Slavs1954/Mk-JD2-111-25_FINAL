package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Audit;
import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.annotaions.AuditPoint;
import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.service.api.ICommonJwtService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;


@Aspect
@Component
@AllArgsConstructor
public class AuditAspect {
    private final WebClient auditWebClient;
    private final ICommonJwtService commonJwtService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void controllerMethods() {}


    @AfterReturning(pointcut = "@annotation(auditPoint)", returning = "result")
    public void auditAfter(JoinPoint joinPoint, AuditPoint auditPoint, Object result) {
        try {
            Audit audit = buildAudit(joinPoint, auditPoint, result);
            sendAudit(audit);
        } catch (Exception e) {
            // Silent fail
        }
    }

    private Audit buildAudit(JoinPoint joinPoint, AuditPoint auditPoint, Object result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String serviceName = null;
        UUID userUuid = null;
        UserRole userRole = null;
        if (auth != null) {
            Object principal = auth.getPrincipal();
            String role = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse(null);

            if ("ROLE_SERVICE".equals(role)) {
                serviceName = (String) principal;
            } else if ("ROLE_USER".equals(role) || "ROLE_ADMIN".equals(role)) {
                userUuid = UUID.fromString((String) principal);
                userRole = UserRole.valueOf((String) principal);
            }
            else {
                serviceName = "Anonymous";
            }
        }
        User user = null;
        if (serviceName != null) {
            user = User.builder()
                    .fio(serviceName)
                    .uuid(UUID.randomUUID())
                    .mail("null")
                    .role(UserRole.MANAGER)
                    .build();
        }
        else if (userUuid != null) {
            user = User.builder()
                    .uuid(userUuid)
                    .mail("null")
                    .role(userRole)
                    .fio("null")
                    .build();
        }

        return Audit.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(System.currentTimeMillis())
                .user(user)
                .text(buildText(joinPoint, result))
                .type(auditPoint.type())
                .id(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())
                .build();
    }
    private String buildText(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().getName();
        return "Executed method " + method;
    }

    private void sendAudit(Audit audit) {
        String jwt = commonJwtService.generateServiceToken("CommonAuditAspect");
        auditWebClient.post()
                .headers(headers -> headers.setBearerAuth(jwt))
                .bodyValue(audit)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }


}
