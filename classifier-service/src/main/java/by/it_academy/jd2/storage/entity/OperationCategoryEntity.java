package by.it_academy.jd2.storage.entity;

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
@Table(name = "operation_category", schema = "finance_app")
@NoArgsConstructor
@AllArgsConstructor
public class OperationCategoryEntity {
    @Id
    private UUID uuid;

    @Column(name = "dt_create")
    private long dtCreate;

    @Column(name = "dt_update")
    private long dtUpdate;

    @Column(name = "title")
    private String title;
}
