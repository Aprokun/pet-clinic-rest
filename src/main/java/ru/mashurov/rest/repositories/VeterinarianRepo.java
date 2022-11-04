package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.pojo.VeterinarianJ;

import java.util.Set;

public interface VeterinarianRepo extends CrudRepository<VeterinarianJ, Long> {

    Set<VeterinarianJ> findAllByClinicId(final Long clinicId);
}
