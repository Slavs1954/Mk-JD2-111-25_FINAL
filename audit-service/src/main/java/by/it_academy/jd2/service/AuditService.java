package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Audit;
import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.service.api.IAuditService;
import by.it_academy.jd2.storage.api.IAuditStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuditService implements IAuditService {
    private final IAuditStorage auditStorage;

    @Override
    public Page getPage(int page, int size) {
        return auditStorage.getPage(page, size);
    }

    @Override
    public Audit getAudit(UUID uuid) {
        return auditStorage.getAudit(uuid);
    }

    @Override
    public void saveAudit(Audit audit) {
        auditStorage.saveAudit(audit);
    }
}
