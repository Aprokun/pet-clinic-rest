package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.pojo.PetJ;

import java.util.Set;

@Repository
public interface PetRepo extends CrudRepository<PetJ, Long> {

    Set<PetJ> findAllByUserId(final Long userId);
}
