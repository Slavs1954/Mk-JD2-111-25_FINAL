package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.PageOfUser;
import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.dto.enums.UserStatus;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.entity.UserEntity;
import by.it_academy.jd2.storage.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    @Transactional
    public void updateStatus(UUID uuid, UserStatus userStatus) {
        UserEntity found = userRepository.findById(uuid).orElseThrow();
        found.setStatus(userStatus);
        userRepository.save(found);
    }

    @Override
    public PageOfUser get(int page, int size) {
        Page<UserEntity> entityPage = userRepository.findAll(PageRequest.of(page, size));
        List<User> content = new ArrayList<>();
        for (UserEntity entity : entityPage.getContent()) {
            content.add(User.builder()
                    .uuid(entity.getUuid())
                    .dt_create(entity.getDt_create())
                    .dt_update(entity.getDt_update())
                    .mail(entity.getMail())
                    .fio(entity.getFio())
                    .role(entity.getRole())
                    .status(entity.getStatus())
                    .build());
        }
        return PageOfUser.builder()
                .number(entityPage.getNumber())
                .size(entityPage.getSize())
                .total_pages(entityPage.getTotalPages())
                .total_elements(entityPage.getTotalElements())
                .first(entityPage.isFirst())
                .number_of_elements(entityPage.getNumberOfElements())
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
                .dt_create(found.getDt_create())
                .dt_update(found.getDt_update())
                .mail(found.getMail())
                .fio(found.getFio())
                .role(found.getRole())
                .status(found.getStatus())
                .build();
    }

    @Override
    public User getByMail(String mail) {
        UserEntity entity = userRepository.findByMail(mail).orElseThrow();
        return User.builder()
                .uuid(entity.getUuid())
                .dt_create(entity.getDt_create())
                .dt_update(entity.getDt_update())
                .mail(entity.getMail())
                .fio(entity.getFio())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();
    }

}
