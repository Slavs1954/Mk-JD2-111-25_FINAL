package by.it_academy.jd2.service.api;

import java.util.UUID;

public interface IMailerService {
    boolean add(UUID userId, String code);
    boolean verify(UUID userId, String code);
    void sendMail(UUID userId, String mail, String code);
    boolean isVerified(UUID userId);
}
