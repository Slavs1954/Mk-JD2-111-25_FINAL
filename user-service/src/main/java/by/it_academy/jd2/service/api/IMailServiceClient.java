package by.it_academy.jd2.service.api;

import java.util.UUID;

public interface IMailServiceClient {
    void add(UUID userId);
    boolean verify(UUID userId, String code);
    boolean isVerified(UUID userId);
}
