package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    boolean existsByTelegramId(final Long telegramId);

    Optional<User> findByTelegramId(final Long telegramId);
}
