package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Account;
import by.it_academy.jd2.dto.PageOfAccount;

import java.util.UUID;

public interface IAccountService {
    void create(UUID userUuid, Account account);
    PageOfAccount getPage(UUID userUuid, int page, int size);
    Account getAccount(UUID uuid);
    void update(UUID uuid, long dtUpdate, Account account);
}
