package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.enums.UserRole;
import io.jsonwebtoken.Claims;

import java.util.UUID;

public interface IJwtService {
    String generateToken(UUID uuid, String mail, UserRole role);
    Claims parseToken(String token);
}
