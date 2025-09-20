package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;

import java.util.UUID;

public interface IClassifierServiceClient {
    Currency getCurrency(String title);
    OperationCategory getCategory(String title);
    Currency getCurrency(UUID uuid);
    OperationCategory getCategory(UUID uuid);
}
