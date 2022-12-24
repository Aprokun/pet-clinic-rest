package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.VeterinarianTimetable;

import java.util.Optional;

@Repository
public interface VeterinarianTimetableRepo extends CrudRepository<VeterinarianTimetable, Long> {

	Optional<VeterinarianTimetable> findByVeterinarianId(final Long veterinarianId);
}
