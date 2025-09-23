package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Page;
import by.it_academy.jd2.dto.ScheduledOperation;
import by.it_academy.jd2.service.api.ISchedulerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/scheduler/operation")
@AllArgsConstructor
public class SchedulerController {
    private final ISchedulerService schedulerService;

    @PostMapping
    ResponseEntity<String> add(@RequestBody ScheduledOperation scheduledOperation) {
        schedulerService.add(scheduledOperation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    ResponseEntity<Page> getPage(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok().body(schedulerService.getPage(page.orElse(0), size.orElse(20)));
    }
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    ResponseEntity<String> update(@PathVariable long dt_update, @PathVariable UUID uuid,
                                  @RequestBody ScheduledOperation scheduledOperation) {
        schedulerService.update(uuid, dt_update, scheduledOperation);
        return ResponseEntity.ok().build();
    }


}
