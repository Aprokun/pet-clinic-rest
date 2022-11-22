package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Pet;
import ru.mashurov.rest.repositories.PetRepo;
import ru.mashurov.rest.utils.ErrorMessages;

import java.util.Optional;
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

    public Pet save(final Pet pet) {
        return petRepo.save(pet);
    }

    public Set<Pet> findAllByUserId(final Long userId) {

        if (userService.existByUserId(userId)) {
            return petRepo.findAllByUserId(userId);
        } else {
            //TODO 404
            throw new RuntimeException(USER_NOT_REGISTERED);
        }
    }

    public Pet findById(final Long id) {

        final Optional<Pet> pet = petRepo.findById(id);

        if (pet.isPresent()) {
            return pet.get();
        } else {
            throw new RuntimeException(ErrorMessages.PET_NOT_EXIST);
        }
    }

    public void deleteById(final Long id) {
        petRepo.deleteById(id);
    }
}
