package by.it_academy.jd2.mail.controller;

import by.it_academy.jd2.mail.service.api.IMailerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/mail")
@AllArgsConstructor
public class MailController {

    IMailerService mailerService;

    @GetMapping
    public ResponseEntity<Boolean> isVerified(@RequestParam String mail) {
        return ResponseEntity.ok(mailerService.isVerified(mail));
    }

    @PutMapping
    public ResponseEntity<Boolean> verify(@RequestParam String mail, @RequestParam String code) {
        return ResponseEntity.ok(mailerService.verify(mail, code));
    }

    @PostMapping
    public ResponseEntity<Boolean> add(@RequestParam String mail, @RequestParam String code) {
        return ResponseEntity.ok(mailerService.add(mail, code));
    }

}
