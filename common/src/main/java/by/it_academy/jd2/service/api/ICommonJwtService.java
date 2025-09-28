package by.it_academy.jd2.service.api;

import io.jsonwebtoken.Claims;

public interface ICommonJwtService {
    String generateServiceToken(String serviceName);
    Claims parseToken(String token);
    boolean isExpired(String token);
}
