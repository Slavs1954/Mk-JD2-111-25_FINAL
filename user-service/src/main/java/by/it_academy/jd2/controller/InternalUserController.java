package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.UserVerification;

import by.it_academy.jd2.dto.annotaions.AuditPoint;
import by.it_academy.jd2.dto.enums.Type;
import by.it_academy.jd2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class InternalUserController {

    private UserService userService;

    @PostMapping
    @AuditPoint(type = Type.USER)
    public ResponseEntity<List<UserVerification>> getMails(@RequestBody List<Pair<UUID, String>> idCodePairs) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getVerificationData(idCodePairs));
    }
    @GetMapping
    @AuditPoint(type = Type.USER)
    public ResponseEntity<UserVerification> getUserVerification(@RequestParam UUID userId, @RequestParam String code) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getVerificationData(userId, code));
    }

}
