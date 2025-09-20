package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Account;
import by.it_academy.jd2.dto.PageOfAccount;
import by.it_academy.jd2.service.api.IClassifierServiceClient;
import by.it_academy.jd2.storage.api.IAccountStorage;
import by.it_academy.jd2.storage.entity.AccountEntity;
import by.it_academy.jd2.storage.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountStorage implements IAccountStorage {

    private final AccountRepository accountRepository;
    private final IClassifierServiceClient classifierServiceClient;

    @Override
    @Transactional
    public void save(UUID userUuid, Account account) {

        UUID currencyUuid;
        if(isValidUuid(account.getCurrency())) {
            currencyUuid = classifierServiceClient.getCurrency(UUID.fromString(account.getCurrency())).getUuid();
        }
        else {
            currencyUuid = classifierServiceClient.getCurrency(account.getCurrency()).getUuid();
        }

        AccountEntity accountEntity = AccountEntity.builder()
                .uuid(account.getUuid())
                .dtCreate(account.getDtCreate())
                .dtUpdate(account.getDtUpdate())
                .title(account.getTitle())
                .description(account.getDescription())
                .balance(account.getBalance())
                .type(account.getType())
                .balance(account.getBalance())
                .currency(currencyUuid)
                .userUuid(userUuid)
                .build();
        accountRepository.save(accountEntity);
    }

    @Override
    public Account getAccount(UUID uuid) {
        AccountEntity entity = accountRepository.getReferenceById(uuid);
        return Account.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .balance(entity.getBalance())
                .type(entity.getType())
                .balance(entity.getBalance())
                .build();
    }

    @Override
    @Transactional
    public void update(UUID uuid, Account account, long dtUpdate) {
        AccountEntity entity = accountRepository.getReferenceById(uuid);
        if (!Objects.equals(account.getTitle(), entity.getTitle())) {
            entity.setTitle(account.getTitle());
        }
        if (!Objects.equals(account.getDescription(), entity.getDescription())) {
            entity.setDescription(account.getDescription());
        }
        if (!Objects.equals(account.getBalance(), entity.getBalance())) {
            entity.setBalance(account.getBalance());
        }
        if (account.getType() != entity.getType()) {
            entity.setType(account.getType());
        }
        entity.setDtUpdate(dtUpdate);
        accountRepository.save(entity);
    }

    @Override
    public PageOfAccount getPage(UUID userUuid, int page, int size) {
        Page<AccountEntity> accountPage = accountRepository.findByUserUuid(userUuid, PageRequest.of(page, size));
        List<Account> content = new ArrayList<>();
        for (AccountEntity accountEntity : accountPage.getContent()) {
            content.add(Account.builder()
                    .uuid(accountEntity.getUuid())
                    .dtCreate(accountEntity.getDtCreate())
                    .dtUpdate(accountEntity.getDtUpdate())
                    .title(accountEntity.getTitle())
                    .description(accountEntity.getDescription())
                    .balance(accountEntity.getBalance())
                    .type(accountEntity.getType())
                    .balance(accountEntity.getBalance())
                    .build());
        }
            return PageOfAccount.builder()
                    .number(accountPage.getNumber())
                    .size(accountPage.getSize())
                    .totalPages(accountPage.getTotalPages())
                    .totalElements(accountPage.getTotalElements())
                    .first(accountPage.isFirst())
                    .numberOfElements(accountPage.getNumberOfElements())
                    .last(accountPage.isLast())
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
