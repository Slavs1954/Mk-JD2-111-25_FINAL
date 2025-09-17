package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.dto.PageOfCurrency;
import by.it_academy.jd2.dto.PageOfOperationCategory;

public interface IClassifierService {
    void addCurrency(Currency currency);
    void addOperationCategory(OperationCategory operationCategory);
    PageOfOperationCategory getPageOfOperationCategory(int page, int size);
    PageOfCurrency getPageOfCurrency(int page, int size);
}
