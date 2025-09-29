package by.it_academy.jd2.dto;


import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    private final UUID uuid;
    private final long dtCreate;
    private final long dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
}
