package by.it_academy.jd2.user.dto;

import by.it_academy.jd2.user.dto.enums.UserRole;
import by.it_academy.jd2.user.dto.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    final UUID uuid;
    final long dt_create;
    final long dt_update;
    String mail;
    String fio;
    UserRole role;
    UserStatus status;
}
