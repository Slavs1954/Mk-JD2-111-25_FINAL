package by.it_academy.jd2.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "operations", schema = "finance_app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationEntity {

    @Id
    private UUID uuid;

    @Column(name = "dt_create")
    private long dtCreate;

    @Column(name = "dt_update")
    private long dtUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_uuid", nullable = false)
    private AccountEntity account;

    @Column(name = "date")
    private Long date;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private UUID category;

    @Column(name = "value", nullable = false, precision = 20, scale = 2)
    private BigDecimal value;

    @Column(name = "currency")
    private UUID currency;
}
