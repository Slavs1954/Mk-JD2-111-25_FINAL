package by.it_academy.jd2.service;


import by.it_academy.jd2.config.security.properties.JwtProperties;
import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.service.api.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

@Service
public class JwtService implements IJwtService {

    private final JwtProperties jwtProperties;
    private final Key key;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(this.jwtProperties.getSecret().getBytes());
    }

    @Override
    public String generateToken(UUID uuid, String mail, UserRole role) {
        return Jwts.builder()
                .subject(uuid.toString())
                .claim("mail", mail)
                .claim("role", role.toString())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(jwtProperties.getExpiration())))
                .signWith(key)
                .compact();
    }

}
