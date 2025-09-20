package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.dto.PageOfCurrency;
import by.it_academy.jd2.dto.PageOfOperationCategory;

import java.util.UUID;

public interface IClassifierStorage {
    void addCurrency(Currency currency);
    void addOperationCategory(OperationCategory operationCategory);
    PageOfCurrency getAllCurrencies(int page, int size);
    PageOfOperationCategory getAllOperationCategories(int page, int size);
    Currency getCurrencyByName(String name);
    OperationCategory getOperationCategoryByName(String title);
    Currency getCurrencyByUuid(UUID uuid);
    OperationCategory getOperationCategoryByUuid(UUID uuid);
}
