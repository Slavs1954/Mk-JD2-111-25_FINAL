package by.it_academy.jd2.storage.entity;


import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "users", schema = "finance_app")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    UUID uuid;
    @Column(name = "dt_create")
    long dtCreate;
    @Column(name = "dt_update")
    long dtUpdate;
    @Column(name = "mail")
    String mail;
    @Column(name = "fio")
    String fio;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    UserRole role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    UserStatus status;
    @Column(name = "password")
    String password;
}
