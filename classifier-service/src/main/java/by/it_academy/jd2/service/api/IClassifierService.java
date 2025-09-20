package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.dto.PageOfCurrency;
import by.it_academy.jd2.dto.PageOfOperationCategory;

import java.util.UUID;

public interface IClassifierService {
    void addCurrency(Currency currency);
    void addOperationCategory(OperationCategory operationCategory);
    PageOfOperationCategory getPageOfOperationCategory(int page, int size);
    PageOfCurrency getPageOfCurrency(int page, int size);
    Currency getCurrencyByName(String name);
    OperationCategory getOperationCategoryByName(String name);
    Currency getCurrencyByUuid(UUID uuid);
    OperationCategory getOperationCategoryByUuid(UUID uuid);
}
