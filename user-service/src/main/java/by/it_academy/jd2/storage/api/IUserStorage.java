package by.it_academy.jd2.storage.api;



import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.dto.enums.UserStatus;

import java.util.UUID;

public interface IUserStorage {
    boolean add(UserCreate user);
    boolean update(UUID uuid, long dt_update, UserCreate user);
    void updateStatus(UUID uuid, UserStatus userStatus);
    Page get(int page, int size);
    String getPassword(UUID uuid);
    User getByUuid(UUID uuid);
    User getByMail(String mail);
}
