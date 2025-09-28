package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.dto.enums.UserStatus;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.entity.UserEntity;
import by.it_academy.jd2.storage.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserStorage implements IUserStorage {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public boolean add(UserCreate user) {
        userRepository.save(
                UserEntity.builder()
                        .uuid(user.getUuid())
                        .dtCreate(user.getDtCreate())
                        .dtUpdate(user.getDtUpdate())
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
    public boolean update(UUID uuid, long dtUpdate, UserCreate user) {
        UserEntity existing = userRepository.findById(uuid).orElseThrow();
        existing.setDtUpdate(dtUpdate);

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
    @Transactional
    public void updateStatus(UUID uuid, UserStatus userStatus) {
        UserEntity found = userRepository.findById(uuid).orElseThrow();
        found.setStatus(userStatus);
        userRepository.save(found);
    }


    @Override
    public Page get(int page, int size) {
        org.springframework.data.domain.Page<UserEntity> entityPage = userRepository.findAll(PageRequest.of(page, size));
        List<User> content = new ArrayList<>();
        for (UserEntity entity : entityPage.getContent()) {
            content.add(User.builder()
                    .uuid(entity.getUuid())
                    .dtCreate(entity.getDtCreate())
                    .dtUpdate(entity.getDtUpdate())
                    .mail(entity.getMail())
                    .fio(entity.getFio())
                    .role(entity.getRole())
                    .status(entity.getStatus())
                    .build());
        }
        return Page.builder()
                .number(entityPage.getNumber())
                .size(entityPage.getSize())
                .totalPages(entityPage.getTotalPages())
                .totalElements(entityPage.getTotalElements())
                .first(entityPage.isFirst())
                .numberOfElements(entityPage.getNumberOfElements())
                .last(entityPage.isLast())
                .content(content)
                .build();
    }

    @Override
    public String getPassword(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow().getPassword();
    }


    @Override
    public User getByUuid(UUID uuid) {
        UserEntity found = userRepository.findById(uuid).orElseThrow();
        return User.builder()
                .uuid(found.getUuid())
                .dtCreate(found.getDtCreate())
                .dtUpdate(found.getDtUpdate())
                .mail(found.getMail())
                .fio(found.getFio())
                .role(found.getRole())
                .status(found.getStatus())
                .build();
    }

    @Override
    public User getByMail(String mail) {
        UserEntity entity = userRepository.findByMail(mail).orElse(null);
        if (entity == null) {
            return null;
        }
        return User.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .mail(entity.getMail())
                .fio(entity.getFio())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();
    }

}
