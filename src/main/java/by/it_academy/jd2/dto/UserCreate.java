package by.it_academy.jd2.dto;

import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import by.it_academy.jd2.validation.groups.OnCreate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserCreate {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    final UUID uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    final long dt_create;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    final long dt_update;

    @NotBlank(groups = OnCreate.class)
    @Email(groups = OnCreate.class)
    String mail;

    @NotBlank(groups = OnCreate.class)
    String fio;

    @NotNull(groups = OnCreate.class)
    UserRole role;

    @NotNull(groups = OnCreate.class)
    UserStatus status;

    @NotBlank(groups = OnCreate.class)
    String password;

    @JsonCreator
    public UserCreate(@JsonProperty("mail") String mail,
                      @JsonProperty("fio") String fio,
                      @JsonProperty("role") UserRole role,
                      @JsonProperty("status") UserStatus status,
                      @JsonProperty("password") String password) {
        this.uuid = UUID.randomUUID();
        this.dt_create = System.currentTimeMillis();
        this.dt_update = System.currentTimeMillis();
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;

    }
}
