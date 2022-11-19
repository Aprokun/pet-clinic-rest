package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Pet;
import ru.mashurov.rest.repositories.PetRepo;

import java.util.Set;

import static ru.mashurov.rest.utils.ErrorMessages.USER_NOT_REGISTERED;

@Service
@AllArgsConstructor
public class PetService {

    private final PetRepo petRepo;
    private final UserService userService;

    public Boolean existById(final Long id) {

        return petRepo.existsById(id);
    }

    public Set<Pet> findAllByUserId(final Long userId) {

        if (userService.existById(userId)) {

            return petRepo.findAllByUserId(userId);

        } else {

            //TODO 404
            throw new RuntimeException(USER_NOT_REGISTERED);
        }

    }
}
