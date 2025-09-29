package by.it_academy.jd2.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegistration {
    @NotBlank(message = "Mail must not be empty")
    @Email(message = "Mail must be a valid email")
    private String mail;
    private String fio;
    @NotBlank(message = "Password must not be empty")
    private String password;
}
