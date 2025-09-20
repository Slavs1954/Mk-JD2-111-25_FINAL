package by.it_academy.jd2.storage.repository;

import by.it_academy.jd2.storage.entity.OperationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.UUID;

public interface OperationCategoryRepository extends JpaRepository<OperationCategoryEntity, UUID> {
    Optional<OperationCategoryEntity> findByTitle(String title);
}
