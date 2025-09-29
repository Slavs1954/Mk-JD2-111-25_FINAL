package by.it_academy.jd2.dto;


import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import by.it_academy.jd2.groups.OnCreate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserCreate {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Builder.Default
    private long dtCreate = Instant.now().toEpochMilli();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Builder.Default()
    private long dtUpdate = Instant.now().toEpochMilli();

    @NotBlank(groups = OnCreate.class, message = "Mail must not be empty")
    @Email(groups = OnCreate.class, message = "Mail must be a valid email")
    private String mail;

    private String fio;

    @NotNull(groups = OnCreate.class, message = "Role must not be null")
    private UserRole role;

    @NotNull(groups = OnCreate.class, message = "Status must not be null")
    private UserStatus status;

    @NotBlank(groups = OnCreate.class)
    private String password;

    @JsonCreator
    public UserCreate(@JsonProperty("mail") String mail,
                      @JsonProperty("fio") String fio,
                      @JsonProperty("role") UserRole role,
                      @JsonProperty("status") UserStatus status,
                      @JsonProperty("password") String password) {
        this.uuid = UUID.randomUUID();
        this.dtCreate = Instant.now().toEpochMilli();
        this.dtUpdate = Instant.now().toEpochMilli();
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;

    }
}
