package by.it_academy.jd2.storage.entity;

import by.it_academy.jd2.dto.enums.UserRole;
import by.it_academy.jd2.dto.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    long dt_create;
    @Column(name = "dt_update")
    long dt_update;
    @Column(name = "mail")
    String mail;
    @Column(name = "fio")
    String fio;
    @Column(name = "role")
    UserRole role;
    @Column(name = "status")
    UserStatus status;
    @Column(name = "password")
    String password;
}
