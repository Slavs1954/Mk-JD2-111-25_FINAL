package by.it_academy.jd2.filters;

import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

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

            String uuid = claims.getSubject();
            String mail = claims.get("mail").toString();
            UserRole role = UserRole.valueOf(claims.get("role").toString());

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    uuid,
                    null,
                    Collections.emptyList()
            );

            auth.setDetails(new WebAuthenticationDetails(request));

            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            // Ignore
        }
        filterChain.doFilter(request, response);
    }
}
