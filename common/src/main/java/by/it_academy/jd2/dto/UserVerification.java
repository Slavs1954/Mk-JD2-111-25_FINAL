package by.it_academy.jd2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserVerification {
    private UUID userId;
    private String mail;
    private String code;
}
