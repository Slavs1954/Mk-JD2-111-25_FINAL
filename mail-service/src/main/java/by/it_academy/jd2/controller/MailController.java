package by.it_academy.jd2.controller;


import by.it_academy.jd2.service.api.IMailerService;
import by.it_academy.jd2.utils.api.ICodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/mail")
@AllArgsConstructor
public class MailController {

    IMailerService mailerService;
    ICodeGenerator codeGenerator;

    @GetMapping
    public ResponseEntity<Boolean> isVerified(@RequestParam UUID userId) {
        return ResponseEntity.ok(mailerService.isVerified(userId));
    }

    @PutMapping
    public ResponseEntity<Boolean> verify(@RequestParam UUID userId, @RequestParam String code) {
        return ResponseEntity.ok(mailerService.verify(userId, code));
    }

    @PostMapping
    public ResponseEntity<Boolean> add(@RequestParam UUID userId) {
        return ResponseEntity.ok(mailerService.add(userId, codeGenerator.generateCode()));
    }

}
