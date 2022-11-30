package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.Pet;

import java.util.Set;

@Repository
public interface PetRepo extends CrudRepository<Pet, Long> {

    Set<Pet> findAllByUserId(final Long userId);
}
