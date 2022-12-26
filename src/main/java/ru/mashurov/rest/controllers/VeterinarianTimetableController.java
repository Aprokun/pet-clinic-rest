package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.VeterinarianTimetableCreateDto;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.model.TimePeriod;
import ru.mashurov.rest.model.Veterinarian;
import ru.mashurov.rest.model.VeterinarianTimetable;
import ru.mashurov.rest.services.AppointmentRequestService;
import ru.mashurov.rest.services.VeterinarianService;
import ru.mashurov.rest.services.VeterinarianTimetableService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ofPattern;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class VeterinarianTimetableController {

	private final VeterinarianTimetableService veterinarianTimetableService;

	private final AppointmentRequestService appointmentRequestService;

	private final VeterinarianService veterinarianService;

	@GetMapping("/veterinarians/{id}/allow-timetable")
	public ResponseEntity<List<TimePeriod>> findAllowDayTimetableByVeterinarianId(
			@PathVariable final Long id,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam final LocalDate date
	) {

		final VeterinarianTimetable veterinarianTimetable = veterinarianTimetableService.findByVeterinarianId(id);

		List<TimePeriod> timePeriods = null;

		switch (date.getDayOfWeek()) {
			case MONDAY:
				timePeriods = veterinarianTimetable.getTimetable().getMonday();
				break;
			case TUESDAY:
				timePeriods = veterinarianTimetable.getTimetable().getTuesday();
				break;
			case WEDNESDAY:
				timePeriods = veterinarianTimetable.getTimetable().getWednesday();
				break;
			case THURSDAY:
				timePeriods = veterinarianTimetable.getTimetable().getThursday();
				break;
			case FRIDAY:
				timePeriods = veterinarianTimetable.getTimetable().getFriday();
				break;
		}

		final LocalDateTime begin = date.atStartOfDay();
		final LocalDateTime end = date.atStartOfDay().plusHours(23L);

		final List<AppointmentRequest> appointmentRequest
				= appointmentRequestService.findAllByVeterinarianIdAndDateBetween(id, begin, end);

		final List<TimePeriod> allowTimePeriods = getAllowAppointmentTimePeriods(timePeriods, appointmentRequest);

		return ResponseEntity.ok(allowTimePeriods);
	}

	private static List<TimePeriod> getAllowAppointmentTimePeriods(
			final List<TimePeriod> timePeriods, final List<AppointmentRequest> appointmentRequests
	) {

		return timePeriods
				.stream()
				.filter(timePeriod -> appointmentRequests
						.stream()
						.noneMatch(req -> req.getDate().format(ofPattern("HH:mm:ss")).equals(timePeriod.getStart()))
				)
				.collect(Collectors.toList());
	}

	@GetMapping("/veterinarians/{id}/timetable")
	public ResponseEntity<VeterinarianTimetable> findByVeterinarianId(@PathVariable final Long id) {
		return ResponseEntity.ok(veterinarianTimetableService.findByVeterinarianId(id));
	}

	@SneakyThrows
	@PutMapping("/veterinarians/update-timetable")
	public ResponseEntity<Void> updateTimetable(@RequestBody final VeterinarianTimetableCreateDto createDto) {

		final Veterinarian veterinarian = veterinarianService.findById(createDto.getVeterinarianId());

		final VeterinarianTimetable veterinarianTimetable = new VeterinarianTimetable(
				createDto.getId(), createDto.getTimetable(), veterinarian
		);

		veterinarianTimetableService.save(veterinarianTimetable);

		return ResponseEntity.ok().build();
	}
}
