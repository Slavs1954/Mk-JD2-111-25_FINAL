package by.it_academy.jd2.user.service.api;

import by.it_academy.jd2.user.dto.enums.UserRole;
import io.jsonwebtoken.Claims;

import java.util.UUID;

public interface IJwtService {
    String generateToken(UUID uuid, String mail, UserRole role);
    String generateServiceToken(String serviceName);
    Claims parseToken(String token);
}
