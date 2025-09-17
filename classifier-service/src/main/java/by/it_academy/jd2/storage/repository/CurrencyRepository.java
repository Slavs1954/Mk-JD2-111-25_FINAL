package by.it_academy.jd2.storage.repository;

import by.it_academy.jd2.storage.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {
}
