package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.UserCreate;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.validation.groups.OnCreate;
import by.it_academy.jd2.validation.groups.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> create(@Validated(OnCreate.class) @RequestBody UserCreate userCreate) {
        userService.create(userCreate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<String> get(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        userService.get(page.orElse(0), size.orElse(20));
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<String> getSpecific(@PathVariable UUID uuid) {
        userService.getByUuid(uuid);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> update(@PathVariable UUID uuid, @PathVariable long dt_update,
                                         @Validated(OnUpdate.class) @RequestBody UserCreate userCreate) {
        userService.update(uuid, dt_update, userCreate);
        return ResponseEntity.ok().build();
    }


}
