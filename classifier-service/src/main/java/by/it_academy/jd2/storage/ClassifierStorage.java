package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.dto.PageOfCurrency;
import by.it_academy.jd2.dto.PageOfOperationCategory;
import by.it_academy.jd2.storage.api.IClassifierStorage;
import by.it_academy.jd2.storage.entity.CurrencyEntity;
import by.it_academy.jd2.storage.entity.OperationCategoryEntity;
import by.it_academy.jd2.storage.repository.CurrencyRepository;
import by.it_academy.jd2.storage.repository.OperationCategoryRepository;
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
public class ClassifierStorage implements IClassifierStorage {
    private final CurrencyRepository currencyRepository;
    private final OperationCategoryRepository operationCategoryRepository;


    @Override
    @Transactional
    public void addCurrency(Currency currency) {
        CurrencyEntity currencyEntity = CurrencyEntity.builder()
                .description(currency.getDescription())
                .dtCreate(currency.getDtCreate())
                .dtUpdate(currency.getDtUpdate())
                .title(currency.getTitle())
                .uuid(currency.getUuid())
                .build();
        currencyRepository.save(currencyEntity);
    }

    @Override
    @Transactional
    public void addOperationCategory(OperationCategory operationCategory) {
        OperationCategoryEntity operationCategoryEntity = OperationCategoryEntity.builder()
                .uuid(operationCategory.getUuid())
                .title(operationCategory.getTitle())
                .dtCreate(operationCategory.getDtCreate())
                .dtUpdate(operationCategory.getDtUpdate())
                .build();
        operationCategoryRepository.save(operationCategoryEntity);
    }

    @Override
    public PageOfCurrency getAllCurrencies(int page, int size) {
        Page<CurrencyEntity> currencyPage = currencyRepository.findAll(PageRequest.of(page, size));
        List<Currency> content = new ArrayList<>();
        for (CurrencyEntity currencyEntity : currencyPage.getContent()) {
            content.add(
                    Currency.builder()
                            .uuid(currencyEntity.getUuid())
                            .dtCreate(currencyEntity.getDtCreate())
                            .dtUpdate(currencyEntity.getDtUpdate())
                            .title(currencyEntity.getTitle())
                            .description(currencyEntity.getDescription())
                            .build());
        }
        return PageOfCurrency.builder()
                .number(currencyPage.getNumber())
                .size(currencyPage.getSize())
                .totalPages(currencyPage.getTotalPages())
                .totalElements(currencyPage.getTotalElements())
                .first(currencyPage.isFirst())
                .numberOfElements(currencyPage.getNumberOfElements())
                .last(currencyPage.isLast())
                .content(content)
                .build();
    }

    @Override
    public PageOfOperationCategory getAllOperationCategories(int page, int size) {
        Page<OperationCategoryEntity> currencyPage = operationCategoryRepository.findAll(PageRequest.of(page, size));
        List<OperationCategory> content = new ArrayList<>();
        for (OperationCategoryEntity operationCategoryEntity : currencyPage.getContent()) {
            content.add(OperationCategory.builder()
                    .uuid(operationCategoryEntity.getUuid())
                    .dtCreate(operationCategoryEntity.getDtCreate())
                    .dtUpdate(operationCategoryEntity.getDtUpdate())
                    .title(operationCategoryEntity.getTitle())
                    .build()
            );
        }
        return PageOfOperationCategory.builder()
                .number(currencyPage.getNumber())
                .size(currencyPage.getSize())
                .totalPages(currencyPage.getTotalPages())
                .totalElements(currencyPage.getTotalElements())
                .first(currencyPage.isFirst())
                .numberOfElements(currencyPage.getNumberOfElements())
                .last(currencyPage.isLast())
                .content(content)
                .build();
    }

    @Override
    public Currency getCurrencyByName(String title) {
        CurrencyEntity entity = currencyRepository.findByTitle(title).orElseThrow();
        return  Currency.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public OperationCategory getOperationCategoryByName(String title) {
        OperationCategoryEntity entity = operationCategoryRepository.findByTitle(title).orElseThrow();
        return OperationCategory.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .build();
    }

    @Override
    public Currency getCurrencyByUuid(UUID uuid) {
        CurrencyEntity entity = currencyRepository.findById(uuid).orElseThrow();
        return  Currency.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public OperationCategory getOperationCategoryByUuid(UUID uuid) {
        OperationCategoryEntity entity = operationCategoryRepository.findById(uuid).orElseThrow();
        return OperationCategory.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .build();
    }
}
