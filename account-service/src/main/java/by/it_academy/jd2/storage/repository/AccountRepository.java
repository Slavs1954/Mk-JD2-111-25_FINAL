package by.it_academy.jd2.storage.repository;

import by.it_academy.jd2.storage.entity.AccountEntity;
import by.it_academy.jd2.storage.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    Page<AccountEntity> findByUserUuid(UUID uuid, Pageable pageable);

    UUID uuid(UUID uuid);
}
