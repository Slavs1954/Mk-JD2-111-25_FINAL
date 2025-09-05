package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.enums.UserStatus;
import by.it_academy.jd2.storage.api.IMailStorage;
import by.it_academy.jd2.storage.entity.MailEntity;

import by.it_academy.jd2.storage.entity.UserEntity;
import by.it_academy.jd2.storage.repository.MailRepository;
import by.it_academy.jd2.storage.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class MailStorage implements IMailStorage {

    private final MailRepository mailRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public boolean add(String mail, String code) {
        mailRepository.save(MailEntity.builder()
                .uuid(UUID.randomUUID())
                .user(userRepository.findByMail(mail).orElseThrow())
                .code(code)
                .verified(false)
                .emailCount(0)
                .dt_create(Instant.now().toEpochMilli())
                .build());
        return true;
    }

    @Override
    @Transactional
    public boolean verify(String mail) {
        UserEntity user = userRepository.findByMail(mail).orElseThrow();
        MailEntity mailEntity = mailRepository.findByUser_Uuid(user.getUuid());
        mailEntity.setVerified(true);
        mailEntity.setDt_verified(Instant.now().toEpochMilli());
        user.setStatus(UserStatus.ACTIVE);
        mailRepository.save(mailEntity);
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public String getCode(String mail) {
        return mailRepository.getCodeByMail(mail);
    }
    public List<Pair<String, String>> getUnverifiedMailsAndCodes() {
        List<Pair<String, String>> unverifiedMailsAndCodes = new ArrayList<>();
        for (MailEntity mailEntity : mailRepository.findAllByVerifiedFalse()) {
            if (mailEntity.getEmailCount() > 3) {
                continue;
            }
            unverifiedMailsAndCodes.add(Pair.of(mailEntity.getUser().getMail(), mailEntity.getCode()));
        }
        return unverifiedMailsAndCodes;

    }

    @Override
    @Transactional
    public void incrementVerifiedMailCount(String mail) {
        MailEntity mailEntity = mailRepository.findByUser_Mail(mail);
        mailEntity.setEmailCount(mailEntity.getEmailCount() + 1);
        mailRepository.save(mailEntity);
    }
}
