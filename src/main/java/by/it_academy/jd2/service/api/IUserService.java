package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface IUserService {
    boolean create(UserCreate userCreate);
    boolean update( UUID uuid, long dt_update, UserCreate userCreate);
    User get(int page, int size);
    User getSpecific(UUID uuid);
}
