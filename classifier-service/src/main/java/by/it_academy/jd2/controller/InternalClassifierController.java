package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.service.api.IClassifierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/classifier")
@AllArgsConstructor
public class InternalClassifierController {
    private IClassifierService classifierService;

    @GetMapping("/currency")
    public ResponseEntity<Currency> getSpecificCurrency(@RequestParam String title) {
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.getCurrencyByName(title));
    }

    @GetMapping("/category")
    public ResponseEntity<OperationCategory> getSpecificCategory(@RequestParam String title) {
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.getOperationCategoryByName(title));
    }
    @GetMapping("/currency/{uuid}")
    public ResponseEntity<Currency> getSpecificCurrency(@PathVariable UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.getCurrencyByUuid(uuid));
    }
    @GetMapping("/category/{uuid}")
    public ResponseEntity<OperationCategory> getSpecificCategory(@PathVariable UUID uuid ) {
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.getOperationCategoryByUuid(uuid));
    }

}
