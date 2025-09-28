package by.it_academy.jd2.service;


import by.it_academy.jd2.dto.*;
import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import by.it_academy.jd2.service.api.IMailServiceClient;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.exception.EntityAlreadyExistsException;
import by.it_academy.jd2.service.exception.InvalidCredentialsException;
import by.it_academy.jd2.service.exception.UnverifiedLoginException;
import by.it_academy.jd2.storage.api.IUserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserStorage userStorage;
    private final IMailServiceClient mailServiceClient;

    @Override
    public boolean create(UserCreate userCreate) {
        if (userCreate.getUuid() == null) {
            userCreate.setUuid(UUID.randomUUID());
        }
        userStorage.add(userCreate);
        return true;
    }
    public boolean verify (String code, String mail) {
        User user = userStorage.getByMail(mail);
        boolean isVerified = mailServiceClient.verify(user.getUuid(), code);
        if (!isVerified) {
            return false;
        }
        userStorage.updateStatus(user.getUuid(), UserStatus.ACTIVE);
        return true;
    }

    @Override
    public List<UserVerification> getVerificationData(List<Pair<UUID, String>> idCodePairs) {
        List<UserVerification> userVerifications = new ArrayList<>();
        for (Pair<UUID, String>  idCodePair : idCodePairs) {
            userVerifications.add(
                    UserVerification.builder()
                            .userId(idCodePair.getFirst())
                            .mail(userStorage.getByUuid(idCodePair.getFirst()).getMail())
                            .code(idCodePair.getSecond())
                            .build()
            );
        }
        return userVerifications;
    }
    @Override
    public UserVerification getVerificationData(UUID userId, String code) {
        return UserVerification.builder()
                .userId(userId)
                .mail(userStorage.getByUuid(userId).getMail())
                .code(code)
                .build();
    }

    public boolean create(UserRegistration userRegistration) {

        if (userStorage.getByMail(userRegistration.getMail()) != null) {
            throw new EntityAlreadyExistsException("User already exists");
        }

        UserCreate user = UserCreate.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(Instant.now().toEpochMilli())
                .dtUpdate(Instant.now().toEpochMilli())
                .mail(userRegistration.getMail())
                .fio(userRegistration.getFio())
                .role(UserRole.USER)
                .status(UserStatus.WAITING_ACTIVATION)
                .password(userRegistration.getPassword())
                .build();

        userStorage.add(user);
        mailServiceClient.add(user.getUuid());


        return true;
    }

    @Override
    public boolean update(UUID uuid, long dt_update, UserCreate userCreate) {
        userStorage.update(uuid, dt_update, userCreate);
        return true;
    }

    @Override
    public Page get(int page, int size) {
        return userStorage.get(page, size);
    }

    @Override
    public User getByUuid(UUID uuid) {
        return userStorage.getByUuid(uuid);
    }


    @Override
    public User login(String mail, String password) {
        User user = userStorage.getByMail(mail);
        if (user.getStatus() == UserStatus.WAITING_ACTIVATION) {
            throw new UnverifiedLoginException("User is unverified");
        }
        if (!Objects.equals(userStorage.getPassword(user.getUuid()), password)) {
            throw new InvalidCredentialsException("Wrong credentials");
        }
        return user;
    }
}
