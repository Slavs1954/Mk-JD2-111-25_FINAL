package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.Operation;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.dto.PageOfOperation;
import by.it_academy.jd2.service.ClassifierServiceClient;
import by.it_academy.jd2.service.OperationService;
import by.it_academy.jd2.service.api.IClassifierServiceClient;
import by.it_academy.jd2.storage.api.IOperationStorage;
import by.it_academy.jd2.storage.entity.AccountEntity;
import by.it_academy.jd2.storage.entity.OperationEntity;
import by.it_academy.jd2.storage.repository.AccountRepository;
import by.it_academy.jd2.storage.repository.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class OperationStorage implements IOperationStorage {
    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;
    private final IClassifierServiceClient  classifierServiceClient;

    @Override
    @Transactional
    public void save(UUID accountUuid, Operation operation) {

        UUID categoryUuid, currencyUuid;
        if (isValidUuid(operation.getCategory())) {
            categoryUuid = classifierServiceClient.getCategory(UUID.fromString(operation.getCategory())).getUuid();
        }
        else {
            categoryUuid = classifierServiceClient.getCategory(operation.getCategory()).getUuid();
        }
        if (isValidUuid(operation.getCurrency())) {
            currencyUuid = classifierServiceClient.getCurrency(UUID.fromString(operation.getCurrency())).getUuid();
        }
        else {
            currencyUuid = classifierServiceClient.getCurrency(operation.getCurrency()).getUuid();
        }

        OperationEntity operationEntity = OperationEntity.builder()
                .uuid(operation.getUuid())
                .dtCreate(operation.getDtCreate())
                .dtUpdate(operation.getDtUpdate())
                .account(accountRepository.getReferenceById(accountUuid))
                .date(operation.getDate())
                .description(operation.getDescription())
                .category(categoryUuid)
                .value(operation.getValue())
                .currency(currencyUuid)
                .build();
        operationRepository.save(operationEntity);
    }

    @Override
    @Transactional
    public void update(UUID uuid, UUID OperationUuid, long dtUpdate, Operation operation) {
        OperationEntity entity = operationRepository.getReferenceById(uuid);
        OperationCategory operationCategory = classifierServiceClient.getCategory(operation.getCategory());
        Currency currency = classifierServiceClient.getCurrency(operation.getCurrency());

        if(!entity.getAccount().getUuid().equals(operation.getUuid())) {
            entity.setAccount(accountRepository.getReferenceById(operation.getUuid()));
        }
        if(!entity.getDate().equals(operation.getDate())) {
            entity.setDate(operation.getDate());
        }
        if(!entity.getDescription().equals(operation.getDescription())) {
            entity.setDescription(operation.getDescription());
        }
        if(!operation.getCategory().equals(operationCategory.getTitle())) {
            entity.setCategory(operationCategory.getUuid());
        }
        if(!entity.getValue().equals(operation.getValue())) {
            entity.setValue(operation.getValue());
        }
        if (!operation.getCurrency().equals(operationCategory.getTitle())) {
            entity.setCurrency(operationCategory.getUuid());
        }

        entity.setDtUpdate(dtUpdate);
        operationRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(UUID uuid, UUID OperationUuid, long dtUpdate) {
        AccountEntity entity  = accountRepository.getReferenceById(uuid);
        entity.setDtUpdate(dtUpdate);
        accountRepository.save(entity);
        operationRepository.deleteById(OperationUuid);
    }

    @Override
    public PageOfOperation getPage(UUID uuid, int page, int size) {
        Page<OperationEntity> operationPage = operationRepository.findByAccountUuid(uuid, PageRequest.of(page, size));
        List<Operation> content = new ArrayList<>();
        for (OperationEntity operationEntity : operationPage.getContent()) {
            content.add(Operation.builder()
                    .uuid(operationEntity.getUuid())
                    .dtCreate(operationEntity.getDtCreate())
                    .dtUpdate(operationEntity.getDtUpdate())
                    .date(operationEntity.getDate())
                    .description(operationEntity.getDescription())
                    .category(classifierServiceClient.getCategory(uuid).getTitle())
                    .value(operationEntity.getValue())
                    .currency(classifierServiceClient.getCurrency(operationEntity.getCurrency()).getTitle())
                    .build());
        }
        return PageOfOperation.builder()
                .number(operationPage.getNumber())
                .size(operationPage.getSize())
                .totalPages(operationPage.getTotalPages())
                .totalElements(operationPage.getTotalElements())
                .first(operationPage.isFirst())
                .numberOfElements(operationPage.getNumberOfElements())
                .last(operationPage.isLast())
                .content(content)
                .build();

    }
    private boolean isValidUuid(String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString);

            return uuid.toString().equals(uuidString);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
