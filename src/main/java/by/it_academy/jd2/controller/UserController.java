package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserCreate userCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<String> get(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<String> getSpecific(@PathVariable UUID uuid) {
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> update(@PathVariable UUID uuid, @PathVariable long dt_update, @RequestBody UserCreate userCreate) {
        return ResponseEntity.ok().build();
    }
}
