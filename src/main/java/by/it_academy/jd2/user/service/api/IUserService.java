package by.it_academy.jd2.user.service.api;

import by.it_academy.jd2.user.dto.PageOfUser;
import by.it_academy.jd2.user.dto.User;
import by.it_academy.jd2.user.dto.UserCreate;
import by.it_academy.jd2.user.dto.UserRegistration;

import java.util.UUID;

public interface IUserService {
    boolean create(UserCreate userCreate);
    boolean create(UserRegistration userRegistration);
    boolean update( UUID uuid, long dt_update, UserCreate userCreate);
    PageOfUser get(int page, int size);
    User getByUuid(UUID uuid);
    User login(String mail, String password);
}
