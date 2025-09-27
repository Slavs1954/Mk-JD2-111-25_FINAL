package by.it_academy.jd2.storage.repository;

import by.it_academy.jd2.storage.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {
}
