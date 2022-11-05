package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.pojo.UserJ;
import ru.mashurov.rest.repositories.UserRepo;

import static ru.mashurov.rest.utils.ErrorMessages.USER_NOT_REGISTERED;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public void save(final UserJ user) {
        userRepo.save(user);
    }

    public void deleteById(final Long id) {

        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            //TODO 404
            throw new RuntimeException(USER_NOT_REGISTERED);
        }
    }

    public Boolean existById(final Long id) {
        return userRepo.existsById(id);
    }

    public UserJ findById(final Long id) {
        return userRepo.findById(id).get();
    }
}
