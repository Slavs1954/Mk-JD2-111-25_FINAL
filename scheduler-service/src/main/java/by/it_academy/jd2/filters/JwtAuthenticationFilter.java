package by.it_academy.jd2.filters;

import by.it_academy.jd2.service.api.ICommonJwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final ICommonJwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {


       String authHeader = request.getHeader("Authorization");

       String token = null;
       if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
           token = authHeader.substring(7);
       }

       try {
           Claims claims = jwtService.parseToken(token);

           //Is expired
           if (jwtService.isExpired(token)) {
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           }

           //Service or not
           String tokenType = claims.get("token_type", String.class);

           UsernamePasswordAuthenticationToken auth;

           if ("SERVICE".equals(tokenType)) {
               String serviceName = claims.getSubject();

               auth = new UsernamePasswordAuthenticationToken(
                       serviceName,
                       null,
                       List.of(new SimpleGrantedAuthority("ROLE_SERVICE"))

               );
           } else {
               String uuid = claims.getSubject();
               String mail = claims.get("mail", String.class);
               String role = claims.get("role", String.class);

               auth = new UsernamePasswordAuthenticationToken(
                       uuid,
                       null,
                       List.of(new SimpleGrantedAuthority("ROLE_" + role))
               );
           }
           auth.setDetails(new WebAuthenticationDetails(request));
           SecurityContextHolder.getContext().setAuthentication(auth);
       } catch (Exception e) {
           SecurityContextHolder.clearContext();
       }
       filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/cabinet/login") ||
                path.startsWith("/cabinet/registration") ||
                path.startsWith("/cabinet/verification");
    }
}
