package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserLogin;
import by.it_academy.jd2.dto.UserRegistration;
import by.it_academy.jd2.service.JwtService;
import by.it_academy.jd2.service.api.IJwtService;
import by.it_academy.jd2.service.api.IMailerService;
import by.it_academy.jd2.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cabinet")
@AllArgsConstructor
public class CabinetController {

    private final IJwtService jwtService;
    private final IUserService userService;
    private final IMailerService mailerService;

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
    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        User user = userService.login(userLogin.getMail(), userLogin.getPassword());
        String token = jwtService.generateToken(
                user.getUuid(),
                user.getMail(),
                user.getRole()
        );

        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }

    @GetMapping(path = "/me", produces = "application/json")
    public ResponseEntity<User> self() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UUID uuid = UUID.fromString(auth.getPrincipal().toString());

        User user = userService.getByUuid(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
