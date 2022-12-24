package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.VeterinarianTimetable;
import ru.mashurov.rest.repositories.VeterinarianTimetableRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VeterinarianTimetableService {

	private final VeterinarianTimetableRepo veterinarianTimetableRepo;

	public VeterinarianTimetable findByVeterinarianId(final Long id) {

		final Optional<VeterinarianTimetable> optionalVeterinarianTimetable
				= veterinarianTimetableRepo.findByVeterinarianId(id);

		if (optionalVeterinarianTimetable.isPresent()) {
			return optionalVeterinarianTimetable.get();
		} else {
			throw new RuntimeException("Нет расписание для ветеринара с id - " + id);
		}
	}

	public void save(final VeterinarianTimetable veterinarianTimetable) {
		veterinarianTimetableRepo.save(veterinarianTimetable);
	}
}
