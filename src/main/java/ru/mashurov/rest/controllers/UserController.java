package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.model.User;
import ru.mashurov.rest.services.AppointmentService;
import ru.mashurov.rest.services.UserService;

import java.util.Objects;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AppointmentService appointmentService;

    @GetMapping("/users/{id}/exists")
    public ResponseEntity<Boolean> existByTelegramId(@PathVariable final Long id, @RequestParam final String by) {

        return Objects.equals(by, "telegram")
                ? ResponseEntity.ok(userService.existByTelegramId(id))
                : ResponseEntity.ok(userService.existById(id));
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> save(@RequestBody final User user) {
        return ResponseEntity.ok(userService.save(user));
    }

}
