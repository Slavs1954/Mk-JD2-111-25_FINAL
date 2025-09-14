package by.it_academy.jd2.service;


import by.it_academy.jd2.dto.UserVerification;
import by.it_academy.jd2.service.api.IMailerService;
import by.it_academy.jd2.service.api.IUserServiceClient;
import by.it_academy.jd2.storage.api.IMailStorage;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class VerificationMailScheduler {

    private final IMailStorage mailStorage;
    private final IMailerService mailerService;
    private IUserServiceClient userServiceClient;

    //5 minutes
    @Scheduled(fixedRate = 5 * 60 * 1000)
    @Transactional
    public void resendUnverifiedMails() {
        List< UserVerification> userVerifications = userServiceClient
                .getUserVerification(mailStorage.getUnverifiedUserIdAndCodes());
        for (UserVerification userVerification : userVerifications) {
            mailerService.sendMail(userVerification.getUserId(), userVerification.getMail(), userVerification.getCode());
        }
    }
}
