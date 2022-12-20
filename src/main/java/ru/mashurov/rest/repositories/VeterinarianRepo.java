package ru.mashurov.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.Veterinarian;

public interface VeterinarianRepo extends CrudRepository<Veterinarian, Long> {

	Page<Veterinarian> findAllByClinicId(final Long clinicId, final Pageable pageable);
}
