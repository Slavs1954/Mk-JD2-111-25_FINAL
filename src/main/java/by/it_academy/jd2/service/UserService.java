package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.service.api.IUserService;

import java.util.UUID;

public class UserService implements IUserService {


    @Override
    public boolean create(UserCreate userCreate) {
        return false;
    }

    @Override
    public boolean update(UUID uuid, long dt_update, UserCreate userCreate) {
        return false;
    }

    @Override
    public User get(int page, int size) {
        return null;
    }

    @Override
    public User getSpecific(UUID uuid) {
        return null;
    }
}
