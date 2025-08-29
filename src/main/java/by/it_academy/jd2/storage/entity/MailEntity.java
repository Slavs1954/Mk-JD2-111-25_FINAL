package by.it_academy.jd2.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mail_verification", schema = "finance_app")
public class MailEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "verified", nullable = false)
    private boolean verified;

    @Column(name = "email_count", nullable = false)
    private int emailCount;

    @Column(name = "dt_create")
    private long dt_create;

    @Column(name = "dt_verified")
    private long dt_verified;

}
