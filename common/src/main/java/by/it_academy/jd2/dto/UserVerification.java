package by.it_academy.jd2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserVerification {
    UUID userId;
    String mail;
    String code;
}
