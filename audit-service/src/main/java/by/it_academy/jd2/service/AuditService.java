package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.*;
import by.it_academy.jd2.service.api.IAuditService;
import by.it_academy.jd2.storage.api.IAuditStorage;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuditService implements IAuditService {
    private final IAuditStorage auditStorage;
    private final WebClient userWebClient;
    private final CommonJwtService jwtService;

    @Override
    public Page getPage(int page, int size) {
        return auditStorage.getPage(page, size);
    }

    @Override
    public Audit getAudit(UUID uuid) {
        return auditStorage.getAudit(uuid);
    }

    @Override
    public void saveAudit(Audit audit) {
        UserResponse response = null;
        try {
            if (audit.getUser() != null && audit.getUser().getUuid() != null) {
                String jwt = jwtService.generateServiceToken("AuditService");
                response = userWebClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/{uuid}")
                                .build(audit.getUser().getUuid().toString()))
                        .headers(headers -> headers.setBearerAuth(jwt))
                        .retrieve()
                        .bodyToMono(UserResponse.class)
                        .block();
            }

        }
        catch (Exception e) {//ignore if the user-service is down, we still need to record the action}
        if (response != null) {
                audit.setUser(User.builder()
                        .uuid(response.getUuid())
                        .fio(response.getFio())
                        .role(response.getRole())
                        .mail(response.getMail())
                        .build());
            }
        }

        auditStorage.saveAudit(audit);
    }
}
