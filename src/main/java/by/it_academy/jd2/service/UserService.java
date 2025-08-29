package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.dto.UserRegistration;
import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import by.it_academy.jd2.service.api.IMailerService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.api.IMailStorage;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.utils.api.ICodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserStorage userStorage;
    private final IMailStorage mailStorage;
    private final IMailerService mailerService;
    private final ICodeGenerator codeGenerator;

    @Override
    public boolean create(UserCreate userCreate) {
        userStorage.add(userCreate);
        return true;
    }
    public boolean verify (String code, String mail) {
        mailerService.verify(mail, code);
        return true;
    }

    public boolean create(UserRegistration userRegistration) {
        userStorage.add(UserCreate.builder()
                .uuid(UUID.randomUUID())
                .dt_create(Instant.now().toEpochMilli())
                .dt_update(Instant.now().toEpochMilli())
                .mail(userRegistration.getMail())
                .fio(userRegistration.getFio())
                .role(UserRole.USER)
                .status(UserStatus.WAITING_ACTIVATION)
                .password(userRegistration.getPassword())
                .build());
        mailStorage.add(userRegistration.getMail(), codeGenerator.generateCode());
        mailerService.sendMail(userRegistration.getMail(), mailStorage.getCode(userRegistration.getMail()));
        return true;
    }

    @Override
    public boolean update(UUID uuid, long dt_update, UserCreate userCreate) {
        userStorage.update(uuid, dt_update, userCreate);
        return true;
    }

    @Override
    public User get(int page, int size) {
        return userStorage.get(page, size);
    }

    @Override
    public User getByUuid(UUID uuid) {
        return userStorage.getByUuid(uuid);
    }
}
