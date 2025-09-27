package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Audit;
import by.it_academy.jd2.dto.annotaions.AuditPoint;
import by.it_academy.jd2.service.api.IAuditService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit")
@AllArgsConstructor
public class InternalAuditController {
    private final IAuditService auditService;

    @PostMapping
    ResponseEntity<String> log(@RequestBody Audit audit) {
        auditService.saveAudit(audit);
        return ResponseEntity.ok("Success");
    }
}
