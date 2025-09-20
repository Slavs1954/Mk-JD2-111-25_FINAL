package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Account;
import by.it_academy.jd2.dto.PageOfAccount;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.storage.api.IAccountStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountStorage accountStorage;

    @Override
    public void create(UUID userUuid, Account account) {
        if (account.getUuid() == null) {
            account.setUuid(UUID.randomUUID());
        }
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO);
        }
        account.setDtCreate(Instant.now().toEpochMilli());
        account.setDtUpdate(Instant.now().toEpochMilli());
        accountStorage.save(userUuid, account);
    }

    @Override
    public PageOfAccount getPage(UUID userUuid, int page, int size) {
        return accountStorage.getPage(userUuid, page, size);
    }

    @Override
    public Account getAccount(UUID uuid) {
        return accountStorage.getAccount(uuid);
    }

    @Override
    public void update(UUID uuid, long dtUpdate, Account account) {
        account.setDtUpdate(Instant.now().toEpochMilli());
        accountStorage.update(uuid, account, dtUpdate);
    }
}
