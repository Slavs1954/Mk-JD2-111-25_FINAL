package by.it_academy.jd2.storage.repository;

import by.it_academy.jd2.storage.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<OperationEntity, UUID> {
    Page<OperationEntity> findByAccountUuid(UUID uuid, Pageable pageable);
}
