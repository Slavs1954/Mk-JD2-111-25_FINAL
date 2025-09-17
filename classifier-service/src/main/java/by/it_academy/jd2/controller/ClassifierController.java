package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.dto.PageOfCurrency;
import by.it_academy.jd2.dto.PageOfOperationCategory;
import by.it_academy.jd2.service.api.IClassifierService;
import by.it_academy.jd2.storage.ClassifierStorage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/classifier")
@AllArgsConstructor
public class ClassifierController {

    private IClassifierService classifierService;

    @GetMapping("/currency")
    public ResponseEntity<PageOfCurrency> getCurrency(Optional<Integer> page, Optional<Integer> size) {
        return ResponseEntity.ok().body(classifierService.getPageOfCurrency(page.orElse(0), size.orElse(20) ));
    }

    @PostMapping("/currency")
    public ResponseEntity<String> addCurrency(@RequestBody Currency currency) {
        classifierService.addCurrency(currency);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/operation/category")
    public ResponseEntity<PageOfOperationCategory> getCategory(Optional<Integer> page, Optional<Integer> size) {
        return ResponseEntity.ok().body(classifierService.getPageOfOperationCategory(page.orElse(0), size.orElse(20) ));
    }

    @PostMapping("/operation/category")
    public ResponseEntity<String> addCategory(@RequestBody OperationCategory category) {
        classifierService.addOperationCategory(category);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
