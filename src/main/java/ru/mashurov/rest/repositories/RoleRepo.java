package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

    Optional<Role> findByNameIs(final String roleName);
}
