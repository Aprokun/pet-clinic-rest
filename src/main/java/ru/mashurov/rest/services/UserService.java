package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.User;
import ru.mashurov.rest.repositories.UserRepo;

import java.util.Optional;

import static ru.mashurov.rest.utils.ErrorMessages.USER_NOT_REGISTERED;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User save(final User user) {
        return userRepo.save(user);
    }

    public void deleteById(final Long id) {

        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            //TODO 404
            throw new RuntimeException(USER_NOT_REGISTERED);
        }
    }

    public Boolean existByUserId(final Long id) {
        return userRepo.existsById(id);
    }

    public User findByUserId(final Long id) {
        return userRepo.findById(id).get();
    }

    public User findByTelegramId(final Long telegramId) {

        final Optional<User> user = userRepo.findByTelegramId(telegramId);

        if (user.isPresent()) {
            return user.get();
        } else {
            //TODO 400
            throw new RuntimeException("Пользователя с данным telegramId не существует");
        }
    }

    public Boolean existByTelegramId(final Long telegramId) {
        return userRepo.existsByTelegramId(telegramId);
    }
}
