package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.*;
import by.it_academy.jd2.dto.enums.ReportStatus;
import by.it_academy.jd2.dto.enums.ReportType;
import by.it_academy.jd2.service.api.IReportService;
import by.it_academy.jd2.storage.api.IReportStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportService implements IReportService {

    private final IReportStorage reportStorage;

    @Override
    public void create(Report report) {

        if (report.getStatus() == null) {
            report.setStatus(ReportStatus.LOADED);
        }

        if (report.getType() == null) {
            throw new IllegalArgumentException("The provided report request is not of any known type");
        }
        if (report.getParams() instanceof ReportParamBalance) {
            report.setType(ReportType.BALANCE);
        }
        else if (report.getParams() instanceof ReportParamByDate) {
            report.setType(ReportType.BY_DATE);
        }
        else if (report.getParams() instanceof ReportParamByCategory) {
            report.setType(ReportType.BY_CATEGORY);
        }

        reportStorage.save(report);
    }

    @Override
    public Report get(UUID uuid) {
        return reportStorage.getById(uuid);
    }

    @Override
    public Page getAll(int page, int size) {
       return reportStorage.getPage(page, size);
    }

    @Override
    public boolean exists(UUID uuid) {
        return get(uuid) != null;
    }
}
