package ru.mashurov.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.Admin;
import ru.mashurov.rest.model.Role;

import java.util.Optional;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Long> {

	Optional<Admin> findByLogin(final String login);

	Page<Admin> findAllByRolesContains(final Role role, final Pageable pageable);
}
