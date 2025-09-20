package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Operation;
import by.it_academy.jd2.dto.PageOfOperation;

import java.util.UUID;

public interface IOperationService {
    void save(UUID accountUuid, Operation operation);
    PageOfOperation getPage(UUID uuid, int page, int size);
    void update(UUID uuid, UUID OperationUuid, long dtUpdate, Operation operation);
    void delete(UUID uuid, UUID OperationUuid, long dtUpdate);
}
