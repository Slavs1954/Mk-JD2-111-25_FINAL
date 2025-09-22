package by.it_academy.jd2.storage.entity;

import by.it_academy.jd2.dto.enums.Type;
import by.it_academy.jd2.dto.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "audit", schema = "finance_app")
@AllArgsConstructor
@NoArgsConstructor
public class AuditEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false)
    private Long dtCreate; // linux timestamp

    // User snapshot fields (embedded inside audit)
    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;

    @Column(name = "user_mail", nullable = false, length = 255)
    private String userMail;

    @Column(name = "user_fio", nullable = false, length = 255)
    private String userFio;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false, length = 20)
    private UserRole userRole;

    // Audit details
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Type type;

    @Column(name = "entity_id", nullable = false, length = 255)
    private String entityId;
}