package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserVerification;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.UUID;

public interface IUserServiceClient {
    List<UserVerification> getUserVerification(List<Pair<UUID, String>> idCodePairs);
    UserVerification getUserVerification(UUID id, String code);
}
