package by.it_academy.jd2.storage.entity;

import by.it_academy.jd2.dto.enums.ReportStatus;
import by.it_academy.jd2.dto.enums.ReportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reports", schema = "finance_app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {

    @Id
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID uuid;

    @Column(nullable = false)
    private Long dtCreate;

    @Column(nullable = false)
    private Long dtUpdate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReportStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false, length = 20)
    private ReportType reportType;

    private String description;

    @Column(columnDefinition = "uuid[]")
    private List<UUID> accounts;

    @Column(columnDefinition = "uuid[]")
    private List<UUID> categories;

    private LocalDate dateFrom;
    private LocalDate dateTo;
}