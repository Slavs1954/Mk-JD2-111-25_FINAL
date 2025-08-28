package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserStorage implements IUserStorage {

    private final JpaRepository<UserEntity, UUID> userRepository;

    @Override
    @Transactional
    public boolean add(UserCreate user) {
        userRepository.save(
                UserEntity.builder()
                        .uuid(user.getUuid())
                        .dt_create(user.getDt_create())
                        .dt_update(user.getDt_update())
                        .mail(user.getMail())
                        .fio(user.getFio())
                        .role(user.getRole())
                        .status(user.getStatus())
                        .password(user.getPassword())
                        .build()
        );
        return true;
    }

    @Override
    @Transactional
    public boolean update(UUID uuid, long dt_update, UserCreate user) {
        UserEntity existing = userRepository.findById(uuid).orElseThrow();
        existing.setDt_update(dt_update);

        if (!existing.getMail().equals(user.getMail()) && user.getMail() != null) {
            existing.setMail(user.getMail());
        }
        if (!existing.getFio().equals(user.getFio()) && user.getFio() != null) {
            existing.setFio(user.getFio());
        }
        if (!existing.getRole().equals(user.getRole()) && user.getRole() != null) {
            existing.setRole(user.getRole());
        }
        if (!existing.getStatus().equals(user.getStatus()) && user.getStatus() != null) {
            existing.setStatus(user.getStatus());
        }
        if (!existing.getPassword().equals(user.getPassword()) && user.getPassword() != null) {
            existing.setPassword(user.getPassword());
        }
        userRepository.save(existing);
        return true;
    }

    @Override
    public User get(int page, int size) {
        return null;
    }

    @Override
    public User getByUuid(UUID uuid) {
        UserEntity found = userRepository.findById(uuid).orElseThrow();
        return User.builder()
                .uuid(found.getUuid())
                .dt_create(found.getDt_create())
                .dt_update(found.getDt_update())
                .mail(found.getMail())
                .fio(found.getFio())
                .role(found.getRole())
                .status(found.getStatus())
                .build();
    }
}
