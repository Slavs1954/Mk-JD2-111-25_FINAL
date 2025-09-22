package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Audit;
import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.service.api.IAuditService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/audit")
@AllArgsConstructor
public class AuditController {
    private final IAuditService auditService;

    @GetMapping
    ResponseEntity<Page> getPage(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok().body(auditService.getPage(page.orElse(0), size.orElse(20)));
    }

    @GetMapping("/{uuid}")
    ResponseEntity<Audit> getAudit(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(auditService.getAudit(uuid));
    }

}
