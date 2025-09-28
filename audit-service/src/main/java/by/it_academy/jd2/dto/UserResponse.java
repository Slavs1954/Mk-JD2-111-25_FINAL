package by.it_academy.jd2.dto;


import by.it_academy.jd2.dto.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID uuid;
    private long dtCreate;
    private long dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
}
