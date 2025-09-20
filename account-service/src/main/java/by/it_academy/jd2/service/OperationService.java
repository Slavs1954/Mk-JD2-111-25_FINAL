package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Operation;
import by.it_academy.jd2.dto.PageOfOperation;
import by.it_academy.jd2.service.api.IOperationService;
import by.it_academy.jd2.storage.api.IOperationStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OperationService implements IOperationService {

    private final IOperationStorage  operationStorage;

    @Override
    public void save(UUID accountUuid, Operation operation) {
        if (operation.getUuid() == null) {
            operation.setUuid(UUID.randomUUID());
        }
        operation.setDtCreate(Instant.now().toEpochMilli());
        operation.setDtUpdate(Instant.now().toEpochMilli());
        operationStorage.save(accountUuid, operation);
    }

    @Override
    public PageOfOperation getPage(UUID uuid, int page, int size) {
        return operationStorage.getPage(uuid, page, size);
    }

    @Override
    public void update(UUID uuid, UUID OperationUuid, long dtUpdate, Operation operation) {
        operation.setDtUpdate(Instant.now().toEpochMilli());
        operationStorage.update(uuid, OperationUuid, dtUpdate, operation);
    }

    @Override
    public void delete(UUID uuid, UUID OperationUuid, long dtUpdate) {
        operationStorage.delete(uuid, OperationUuid, dtUpdate);
    }
}
