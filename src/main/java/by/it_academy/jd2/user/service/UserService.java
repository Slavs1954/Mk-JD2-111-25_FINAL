package by.it_academy.jd2.user.service;

import by.it_academy.jd2.user.dto.PageOfUser;
import by.it_academy.jd2.user.dto.User;
import by.it_academy.jd2.user.dto.UserCreate;
import by.it_academy.jd2.user.dto.UserRegistration;
import by.it_academy.jd2.user.dto.enums.UserRole;
import by.it_academy.jd2.user.dto.enums.UserStatus;
import by.it_academy.jd2.mail.service.api.IMailerService;
import by.it_academy.jd2.user.service.api.IJwtService;
import by.it_academy.jd2.user.service.api.IUserService;
import by.it_academy.jd2.mail.storage.api.IMailStorage;
import by.it_academy.jd2.user.storage.api.IUserStorage;
import by.it_academy.jd2.utils.api.ICodeGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final IUserStorage userStorage;
    private final ICodeGenerator codeGenerator;
    private final WebClient mailWebClient;
    private final IJwtService jwtService;

    @Override
    public boolean create(UserCreate userCreate) {
        userStorage.add(userCreate);
        return true;
    }
    public boolean verify (String code, String mail) {
        Boolean response = mailWebClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("code", code)
                        .queryParam("mail", mail)
                        .build())
                .headers(headers -> headers.setBearerAuth(jwtService.generateServiceToken(
                        "UserService")))
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return Boolean.TRUE.equals(response);
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

        String jwt = jwtService.generateServiceToken("UserService");
        log.info("jwt: {}", jwt);
        mailWebClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("mail", userRegistration.getMail())
                        .queryParam("code", codeGenerator.generateCode())
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return true;
    }

    @Override
    public boolean update(UUID uuid, long dt_update, UserCreate userCreate) {
        userStorage.update(uuid, dt_update, userCreate);
        return true;
    }

    @Override
    public PageOfUser get(int page, int size) {
        return userStorage.get(page, size);
    }

    @Override
    public User getByUuid(UUID uuid) {
        return userStorage.getByUuid(uuid);
    }

    @Override
    public User login(String mail, String password) {
        return userStorage.login(mail, password);
    }
}
