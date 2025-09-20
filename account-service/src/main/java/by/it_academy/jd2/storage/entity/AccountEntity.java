package by.it_academy.jd2.storage.entity;

import by.it_academy.jd2.dto.Operation;
import by.it_academy.jd2.dto.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts", schema = "finance_app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    private UUID uuid;

    @Column(name = "dt_create")
    private long dtCreate;

    @Column(name = "dt_update")
    private long dtUpdate;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(precision = 20, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType type;

    @Column(name = "currency", nullable = false)
    private UUID currency;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OperationEntity> operations;

    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;
}
