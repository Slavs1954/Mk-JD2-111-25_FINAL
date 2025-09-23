package by.it_academy.jd2.storage.entity;

import by.it_academy.jd2.dto.enums.TimeUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scheduled_operation", schema = "finance_app")
public class ScheduledOperationEntity {
    @Id
    @Column(name = "uuid", nullable = false, updatable = false)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false)
    private Long dtCreate;

    @Column(name = "dt_update", nullable = false)
    private Long dtUpdate;

    // ---- Schedule ----
    @Column(name = "start_time", nullable = false)
    private Long startTime;

    @Column(name = "stop_time")
    private Long stopTime;

    @Column(name = "repeat_interval", nullable = false)
    private Long repeatInterval;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_unit", nullable = false, length = 10)
    private TimeUnit timeUnit;

    // ---- Operation ----
    @Column(name = "account_uuid", nullable = false)
    private UUID accountUuid;

    @Column(name = "description")
    private String description;

    @Column(name = "value", nullable = false, precision = 19, scale = 2)
    private BigDecimal value;

    @Column(name = "currency_uuid", nullable = false)
    private UUID currencyUuid;

    @Column(name = "category_uuid", nullable = false)
    private UUID categoryUuid;

}
