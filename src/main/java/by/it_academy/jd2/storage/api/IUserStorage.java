package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;

import java.util.UUID;

public interface IUserStorage {
    boolean add(UserCreate user);
    boolean update(UUID uuid, long dt_update, UserCreate user);
    User get(int page, int size);
    User getByUuid(UUID uuid);
}
