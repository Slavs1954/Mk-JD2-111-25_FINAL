package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserLogin;
import by.it_academy.jd2.dto.UserRegistration;
import by.it_academy.jd2.service.api.IMailerService;
import by.it_academy.jd2.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cabinet")
@AllArgsConstructor
public class CabinetController {

    IUserService userService;
    IMailerService mailerService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserRegistration userRegistration) {
        userService.create(userRegistration);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verification(@RequestParam String code, @RequestParam String mail) {
        mailerService.verify(mail, code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {

        // TODO: Add token to Authorization Header
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> self() {
        // TODO: Read UUID from JWT and fetch required user
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
