package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Account;
import by.it_academy.jd2.dto.Operation;
import by.it_academy.jd2.dto.annotaions.AuditPoint;
import by.it_academy.jd2.dto.enums.Type;
import by.it_academy.jd2.service.api.IOperationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class OperationController {

    IOperationService operationService;

    @PostMapping("/{uuid}/operation")
    @AuditPoint(type = Type.OPERATION)
    ResponseEntity<String> addOperation(@PathVariable UUID uuid, @RequestBody Operation operation) {
        operationService.save(uuid, operation);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}/operation")
    @AuditPoint(type = Type.OPERATION)
    ResponseEntity<String> getOperation(@PathVariable UUID uuid, @RequestParam Optional<Integer> page,
                                        @RequestParam Optional<Integer> size) {
        operationService.getPage(uuid, page.orElse(0), size.orElse(20));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}/operation/{uuid_operation}/dt_update/{dt_update}")
    @AuditPoint(type = Type.OPERATION)
    ResponseEntity<String> updateOperation(@PathVariable UUID uuid,
                                           @PathVariable(name = "uuid_operation") UUID operationUuid,
                                           @PathVariable(name = "dt_update") long dtUpdate,
                                           @RequestBody Operation operation) {
        operationService.update(uuid, operationUuid, dtUpdate, operation);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}/operation/{uuid_operation}/dt_update/{dt_update}")
    @AuditPoint(type = Type.OPERATION)
    ResponseEntity<String> deleteOperation(@PathVariable UUID uuid,
                                           @PathVariable(name = "uuid_operation") UUID operationUuid,
                                           @PathVariable(name = "dt_update") long dtUpdate) {

        operationService.delete(uuid, operationUuid, dtUpdate);
        return ResponseEntity.ok().build();
    }
}
