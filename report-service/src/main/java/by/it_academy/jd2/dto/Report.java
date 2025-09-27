package by.it_academy.jd2.dto;

import by.it_academy.jd2.dto.api.ReportParams;
import by.it_academy.jd2.dto.enums.ReportStatus;
import by.it_academy.jd2.dto.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    UUID id;
    long dtCreate;
    long dtUpdate;
    ReportStatus status;
    ReportType type;
    String description;
    ReportParams params;

}
