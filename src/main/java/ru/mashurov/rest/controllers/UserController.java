package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.model.Pet;
import ru.mashurov.rest.model.Region;
import ru.mashurov.rest.model.User;
import ru.mashurov.rest.services.PetService;
import ru.mashurov.rest.services.RegionService;
import ru.mashurov.rest.services.UserService;

import java.util.Objects;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final PetService petService;

    private final RegionService regionService;


    @GetMapping("/users/{id}/exists")
    public ResponseEntity<Boolean> existByTelegramId(@PathVariable final Long id, @RequestParam final String by) {

        return Objects.equals(by, "telegram")
                ? ResponseEntity.ok(userService.existByTelegramId(id))
                : ResponseEntity.ok(userService.existByUserId(id));
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> save(@RequestBody final User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/users/{id}/get")
    public ResponseEntity<User> getUser(@PathVariable final Long id, @RequestParam final String by) {

        return Objects.equals(by, "telegram")
                ? ResponseEntity.ok(userService.findByTelegramId(id))
                : ResponseEntity.ok(userService.findByUserId(id));
    }

    @GetMapping("/users/{id}/pets")
    public ResponseEntity<Set<Pet>> getUserPets(@PathVariable final Long id) {
        return ResponseEntity.ok(petService.findAllByUserId(id));
    }

    @PatchMapping("/user/{id}/set-region")
    public ResponseEntity<Void> setUserRegion(@PathVariable final Long id, @RequestParam final Long code) {

        final Region region = regionService.findById(code);
        final User user = userService.findByUserId(id);

        user.setRegion(region);

        userService.save(user);

        return ResponseEntity.ok().build();
    }
}
