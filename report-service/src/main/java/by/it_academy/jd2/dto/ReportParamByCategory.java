package by.it_academy.jd2.dto;

import by.it_academy.jd2.dto.api.ReportParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportParamByCategory implements ReportParams {
    List<UUID> accounts;
    LocalDate from;
    LocalDate to;
    List<UUID> categories;
}
