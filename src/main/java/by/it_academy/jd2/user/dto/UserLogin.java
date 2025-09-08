package by.it_academy.jd2.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLogin {
    String mail;
    String password;
}
