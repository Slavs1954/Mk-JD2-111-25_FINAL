package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.repository.UserRepository;
import by.it_academy.jd2.storage.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserStorage userStorage;

    @Override
    public boolean create(UserCreate userCreate) {
        userStorage.add(userCreate);
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
