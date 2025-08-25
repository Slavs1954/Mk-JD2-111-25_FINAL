package by.it_academy.jd2.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    WAITING_ACTIVATION,
    ACTIVE,
    DEACTIVATED
}
