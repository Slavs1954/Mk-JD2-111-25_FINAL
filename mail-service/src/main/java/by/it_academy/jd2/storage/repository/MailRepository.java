package by.it_academy.jd2.storage.repository;


import by.it_academy.jd2.storage.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MailRepository extends JpaRepository<MailEntity, UUID> {
    MailEntity findByUserId(UUID userId);

    @Query("SELECT m.code FROM MailEntity m WHERE m.userId = :userId")
    String getCodeByUserId(UUID userId);
    List<MailEntity> findAllByVerifiedFalse();
}
