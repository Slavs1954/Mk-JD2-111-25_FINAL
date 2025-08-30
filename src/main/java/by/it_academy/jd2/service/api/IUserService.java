package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.PageOfUser;
import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.dto.UserRegistration;

import java.util.UUID;

public interface IUserService {
    boolean create(UserCreate userCreate);
    boolean create(UserRegistration userRegistration);
    boolean update( UUID uuid, long dt_update, UserCreate userCreate);
    PageOfUser get(int page, int size);
    User getByUuid(UUID uuid);
}
