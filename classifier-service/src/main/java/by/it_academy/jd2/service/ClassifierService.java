package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.dto.PageOfCurrency;
import by.it_academy.jd2.dto.PageOfOperationCategory;
import by.it_academy.jd2.service.api.IClassifierService;
import by.it_academy.jd2.storage.api.IClassifierStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClassifierService implements IClassifierService {

    private final IClassifierStorage classifierStorage;

    @Override
    public void addCurrency(Currency currency) {
        if (currency.getUuid() == null) {
            currency.setUuid(UUID.randomUUID());
        }
        if (currency.getDtCreate() == 0) {
            currency.setDtCreate(Instant.now().toEpochMilli());
        }
        if (currency.getDtUpdate() == 0) {
            currency.setDtUpdate(Instant.now().toEpochMilli());
        }
        classifierStorage.addCurrency(currency);
    }

    @Override
    public void addOperationCategory(OperationCategory operationCategory) {
        if (operationCategory.getUuid() == null) {
            operationCategory.setUuid(UUID.randomUUID());
        }
        if (operationCategory.getDtCreate() == 0) {
            operationCategory.setDtCreate(Instant.now().toEpochMilli());
        }
        if (operationCategory.getDtUpdate() == 0) {
            operationCategory.setDtUpdate(Instant.now().toEpochMilli());
        }
        classifierStorage.addOperationCategory(operationCategory);
    }

    @Override
    public PageOfOperationCategory getPageOfOperationCategory(int page, int size) {
        return classifierStorage.getAllOperationCategories(page, size);
    }

    @Override
    public PageOfCurrency getPageOfCurrency(int page, int size) {
        return classifierStorage.getAllCurrencies(page, size);
    }

    @Override
    public Currency getCurrencyByName(String name) {
        return classifierStorage.getCurrencyByName(name);
    }

    @Override
    public OperationCategory getOperationCategoryByName(String name) {
        return classifierStorage.getOperationCategoryByName(name);
    }

    @Override
    public Currency getCurrencyByUuid(UUID uuid) {
        return classifierStorage.getCurrencyByUuid(uuid);
    }

    @Override
    public OperationCategory getOperationCategoryByUuid(UUID uuid) {
        return classifierStorage.getOperationCategoryByUuid(uuid);
    }
}
