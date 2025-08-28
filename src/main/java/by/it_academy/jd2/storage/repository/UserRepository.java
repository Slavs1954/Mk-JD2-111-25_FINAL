package by.it_academy.jd2.storage.repository;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
