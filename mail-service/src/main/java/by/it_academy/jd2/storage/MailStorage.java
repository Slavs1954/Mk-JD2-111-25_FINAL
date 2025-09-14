package by.it_academy.jd2.storage;

import by.it_academy.jd2.storage.api.IMailStorage;
import by.it_academy.jd2.storage.entity.MailEntity;
import by.it_academy.jd2.storage.repository.MailRepository;
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

    @Override
    @Transactional
    public boolean add(UUID userId, String code) {
        mailRepository.save(MailEntity.builder()
                .uuid(UUID.randomUUID())
                .userId(userId)
                .code(code)
                .verified(false)
                .emailCount(0)
                .dt_create(Instant.now().toEpochMilli())
                .build());
        return true;
    }

    @Override
    @Transactional
    public boolean verify(UUID userId) {
        MailEntity mailEntity = mailRepository.findByUserId(userId);
        mailEntity.setVerified(true);
        mailEntity.setDt_verified(Instant.now().toEpochMilli());
        mailRepository.save(mailEntity);
        return true;
    }

    @Override
    public String getCode(UUID userId) {
        MailEntity mailEntity = mailRepository.findByUserId(userId);
        return mailEntity.getCode();
    }


    @Override
    @Transactional
    public void incrementVerifiedMailCount(UUID userId) {
        MailEntity mailEntity = mailRepository.findByUserId(userId);
        mailEntity.setEmailCount(mailEntity.getEmailCount() + 1);
        mailRepository.save(mailEntity);
    }

    @Override
    public boolean isVerified(UUID userId) {
        MailEntity mailEntity = mailRepository.findByUserId(userId);
        return mailEntity.isVerified();
    }

    @Override
    public List<Pair<UUID, String>> getUnverifiedUserIdAndCodes() {
        List<Pair<UUID, String>> list = new ArrayList<>();
        for(MailEntity mailEntity : mailRepository.findAllByVerifiedFalse()) {
            list.add(Pair.of(mailEntity.getUuid(), mailEntity.getCode()));
        }
        return list;
    }
}
