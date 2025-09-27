package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.Report;

import java.util.UUID;

public interface IReportStorage  {
    Report getById(UUID id);
    void save(Report report);
    Page getPage(int page, int size);

}
