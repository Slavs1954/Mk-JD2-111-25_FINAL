package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Audit;
import by.it_academy.jd2.dto.Page;

import java.util.UUID;

public interface IAuditStorage {

    Page getPage(int page, int size);
    Audit getAudit(UUID uuid);
}
