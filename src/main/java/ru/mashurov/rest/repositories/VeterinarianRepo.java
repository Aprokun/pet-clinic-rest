package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.Veterinarian;

import java.util.Set;

public interface VeterinarianRepo extends CrudRepository<Veterinarian, Long> {

    Set<Veterinarian> findAllByClinicId(final Long clinicId);
}
