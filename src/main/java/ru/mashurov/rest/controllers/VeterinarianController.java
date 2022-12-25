package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.VeterinarianCreateDto;
import ru.mashurov.rest.dto.VeterinarianUpdateDto;
import ru.mashurov.rest.model.Clinic;
import ru.mashurov.rest.model.Timetable;
import ru.mashurov.rest.model.Veterinarian;
import ru.mashurov.rest.model.VeterinarianTimetable;
import ru.mashurov.rest.services.ClinicService;
import ru.mashurov.rest.services.VeterinarianService;
import ru.mashurov.rest.services.VeterinarianTimetableService;

import java.util.HashSet;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class VeterinarianController {

	private final VeterinarianService veterinarianService;

	private final VeterinarianTimetableService veterinarianTimetableService;

	private final ClinicService clinicService;

	@DeleteMapping("/veterinarians/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable final Long id) {

		veterinarianService.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/veterinarians/{id}")
	public ResponseEntity<Veterinarian> findById(@PathVariable final Long id) {
		return ResponseEntity.ok(veterinarianService.findById(id));
	}

	@PatchMapping("/veterinarians/update")
	public ResponseEntity<Void> update(@RequestBody final VeterinarianUpdateDto updateDto) {

		final Veterinarian oldData = veterinarianService.findById(updateDto.getId());

		final Veterinarian veterinarian = new Veterinarian(
				oldData.getId(), updateDto.getSurname(), updateDto.getName(), updateDto.getPatronymic(),
				updateDto.getExperience(), oldData.getClinic(), oldData.getAppointments(), oldData.getTimetable()
		);

		veterinarianService.save(veterinarian);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/veterinarians/create")
	public ResponseEntity<Void> create(@RequestBody final VeterinarianCreateDto createDto) {

		final Clinic clinic = clinicService.findById(createDto.getClinicId());

		final Veterinarian veterinarian = new Veterinarian(
				null, createDto.getSurname(), createDto.getName(), createDto.getPatronymic(), createDto.getExperience(),
				clinic, new HashSet<>(), null
		);

		veterinarianService.save(veterinarian);

		final VeterinarianTimetable veterinarianTimetable = new VeterinarianTimetable(
				null, new Timetable(), veterinarian
		);

		veterinarianTimetableService.save(veterinarianTimetable);

		return ResponseEntity.ok().build();
	}
}
