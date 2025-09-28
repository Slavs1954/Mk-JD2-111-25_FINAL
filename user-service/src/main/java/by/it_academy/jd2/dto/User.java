package by.it_academy.jd2.dto;


import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
