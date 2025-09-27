package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Account;
import by.it_academy.jd2.dto.PageOfAccount;
import by.it_academy.jd2.dto.annotaions.AuditPoint;
import by.it_academy.jd2.dto.enums.Type;
import by.it_academy.jd2.service.api.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private IAccountService accountService;

    @PostMapping
    @AuditPoint(type = Type.ACCOUNT)
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UUID uuid = UUID.fromString(auth.getPrincipal().toString());
        accountService.create(uuid, account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @AuditPoint(type = Type.ACCOUNT)
    public ResponseEntity<PageOfAccount> getAccountPage(Optional<Integer> page, Optional<Integer> size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UUID uuid = UUID.fromString(auth.getPrincipal().toString());

        return ResponseEntity.ok().body(accountService.getPage(uuid, page.orElse(0), size.orElse(20)));
    }

    @GetMapping("/{uuid}")
    @AuditPoint(type = Type.ACCOUNT)
    public ResponseEntity<Account> getAccount(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(accountService.getAccount(uuid));
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    @AuditPoint(type = Type.ACCOUNT)
    public ResponseEntity<String> updateAccount(@PathVariable UUID uuid, @PathVariable(name = "dt_update") long dtUpdate,
                                                @RequestBody Account account) {

        accountService.update(uuid, dtUpdate, account);
        return ResponseEntity.ok().build();
    }

}
