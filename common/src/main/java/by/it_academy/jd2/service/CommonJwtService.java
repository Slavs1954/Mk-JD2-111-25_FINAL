package by.it_academy.jd2.service;

import by.it_academy.jd2.config.security.properties.JwtProperties;
import by.it_academy.jd2.service.api.ICommonJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;

@Service
public class CommonJwtService implements ICommonJwtService {

    private final JwtProperties jwtProperties;
    private final Key key;

    public CommonJwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(this.jwtProperties.getSecret().getBytes());
    }

    @Override
    public String generateServiceToken(String serviceName) {
        return Jwts.builder()
                .subject(serviceName)
                .claim("token_type", "SERVICE")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(jwtProperties.getExpiration())))
                .signWith(key)
                .compact();
    }


    @Override
    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    @Override
    public boolean isExpired(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration().toInstant().isBefore(Instant.now());
    }
}
