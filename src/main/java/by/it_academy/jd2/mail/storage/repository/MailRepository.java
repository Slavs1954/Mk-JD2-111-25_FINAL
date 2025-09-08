package by.it_academy.jd2.mail.storage.repository;

import by.it_academy.jd2.mail.storage.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MailRepository extends JpaRepository<MailEntity, UUID> {
    MailEntity findByUser_Uuid(UUID userId);
    MailEntity findByUser_Mail(String mail);

    @Query("SELECT m.code FROM MailEntity m WHERE m.user.mail = :mail")
    String getCodeByMail(String mail);
    List<MailEntity> findAllByVerifiedFalse();
    Optional<MailEntity> findByUser_MailAndVerifiedTrue(String mail);
}
