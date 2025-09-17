package by.it_academy.jd2.dto;


import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    private final UUID uuid;
    private final long dtCreate;
    private final long dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
}
