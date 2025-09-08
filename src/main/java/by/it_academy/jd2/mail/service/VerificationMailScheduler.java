package by.it_academy.jd2.mail.service;

import by.it_academy.jd2.mail.service.api.IMailerService;
import by.it_academy.jd2.mail.storage.api.IMailStorage;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class VerificationMailScheduler {

    private final IMailStorage mailStorage;
    private final IMailerService mailerService;

    //5 minutes
    @Scheduled(fixedRate = 5 * 60 * 1000)
    @Transactional
    public void resendUnverifiedMails() {
        for (Pair<String, String> pair: mailStorage.getUnverifiedMailsAndCodes()) {
            mailerService.sendMail(pair.getFirst(), pair.getSecond());
            mailStorage.incrementVerifiedMailCount(pair.getFirst());
        }
    }
}
