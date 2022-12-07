package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.Admin;

import java.util.Optional;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Long> {

	Optional<Admin> findByLogin(final String login);
}
