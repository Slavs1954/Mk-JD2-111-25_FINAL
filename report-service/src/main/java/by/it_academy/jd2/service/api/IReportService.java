package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.Report;

import java.util.UUID;

public interface IReportService {
    void create(Report report);
    Report get(UUID uuid);
    Page getAll(int page, int size);
    boolean exists(UUID uuid);

}
