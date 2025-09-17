package by.it_academy.jd2.dto;


import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import by.it_academy.jd2.groups.OnCreate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserCreate {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    final private UUID uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    final private long dtCreate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    final private long dtUpdate;

    @NotBlank(groups = OnCreate.class)
    @Email(groups = OnCreate.class)
    private String mail;

    @NotBlank(groups = OnCreate.class)
    private String fio;

    @NotNull(groups = OnCreate.class)
    private UserRole role;

    @NotNull(groups = OnCreate.class)
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
