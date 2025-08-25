package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByMail(String mail);
    
}
