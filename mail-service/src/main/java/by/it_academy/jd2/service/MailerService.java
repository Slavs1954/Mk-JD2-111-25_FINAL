package by.it_academy.jd2.service;


import by.it_academy.jd2.dto.UserVerification;
import by.it_academy.jd2.service.api.IMailerService;
import by.it_academy.jd2.service.api.IUserServiceClient;
import by.it_academy.jd2.storage.api.IMailStorage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MailerService implements IMailerService {

    private final IMailStorage mailStorage;
    private final JavaMailSender mailSender;
    private final IUserServiceClient userServiceClient;
    @Override
    public boolean add(UUID userId, String code) {
        UserVerification userVerification = userServiceClient.getUserVerification(userId, code);
        mailStorage.add(userVerification.getUserId(), userVerification.getCode());
        sendMail(userVerification.getUserId(), userVerification.getMail(), userVerification.getCode());
        return true;
    }

    @Override
    public boolean verify(UUID userId, String code) {
        if (!mailStorage.getCode(userId).equals(code)) {
            return false;
        }
        mailStorage.verify(userId);
        return true;
    }

    @Override
    public boolean isVerified(UUID userId) {
       return mailStorage.isVerified(userId);
    }

    @Async
    public void sendMail(UUID userId, String mail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mail);
            helper.setSubject("Verification");
            helper.setText("Your verification code is: " + code);
            mailSender.send(message);
            mailStorage.incrementVerifiedMailCount(userId);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
