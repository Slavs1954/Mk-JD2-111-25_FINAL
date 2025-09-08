package by.it_academy.jd2.user.storage.api;

import by.it_academy.jd2.user.dto.PageOfUser;
import by.it_academy.jd2.user.dto.User;
import by.it_academy.jd2.user.dto.UserCreate;

import java.util.UUID;

public interface IUserStorage {
    boolean add(UserCreate user);
    boolean update(UUID uuid, long dt_update, UserCreate user);
    PageOfUser get(int page, int size);
    User getByUuid(UUID uuid);
    User login(String mail, String password);
}
