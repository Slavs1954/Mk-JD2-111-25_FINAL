package by.it_academy.jd2.service.api;



import by.it_academy.jd2.dto.*;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    boolean create(UserCreate userCreate);
    boolean create(UserRegistration userRegistration);
    boolean update( UUID uuid, long dt_update, UserCreate userCreate);
    boolean verify(String code, String mail);
    List<UserVerification> getVerificationData(List<Pair<UUID, String>>  idCodePairs);
    UserVerification getVerificationData(UUID uuid, String code);
    PageOfUser get(int page, int size);
    User getByUuid(UUID uuid);
    User login(String mail, String password);
}
