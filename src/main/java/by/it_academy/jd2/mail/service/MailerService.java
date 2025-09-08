package by.it_academy.jd2.mail.service;

import by.it_academy.jd2.mail.service.api.IMailerService;
import by.it_academy.jd2.mail.storage.api.IMailStorage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailerService implements IMailerService {

    private final IMailStorage mailStorage;
    private final JavaMailSender mailSender;
    @Override
    public boolean add(String mail, String code) {
        mailStorage.add(mail, code);
        sendMail(mail, code);
        return true;
    }

    @Override
    public boolean verify(String mail, String code) {
        if (!mailStorage.getCode(mail).equals(code)) {
            return false;
        }
        mailStorage.verify(mail);
        return true;
    }

    @Override
    public boolean isVerified(String mail) {
       return mailStorage.isVerified(mail);
    }

    @Async
    public void sendMail(String mail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mail);
            helper.setSubject("Verification");
            helper.setText("Your verification code is: " + code);
            mailSender.send(message);
            mailStorage.incrementVerifiedMailCount(mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
