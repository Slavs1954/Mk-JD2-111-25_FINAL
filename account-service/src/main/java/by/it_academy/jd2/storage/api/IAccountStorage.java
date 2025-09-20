package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Account;
import by.it_academy.jd2.dto.PageOfAccount;

import java.util.UUID;

public interface IAccountStorage {
    void save(UUID userUuid, Account account);
    Account getAccount(UUID uuid);
    void update(UUID uuid, Account account, long dtUpdate);
    PageOfAccount getPage(UUID userUuid, int page, int size);
}
