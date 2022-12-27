package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.UserAppointmentRequestDto;
import ru.mashurov.rest.model.AppointmentRequestStatus;
import ru.mashurov.rest.services.AppointmentRequestService;
import ru.mashurov.rest.services.AppointmentRequestStatusService;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.mashurov.rest.values.AppointmentRequestStatusValues.VISITED;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AppointmentHistoryController {

	private final AppointmentRequestService appointmentRequestService;

	private final AppointmentRequestStatusService appointmentRequestStatusService;

	@GetMapping("/appointments/{petId}/history")
	public ResponseEntity<List<UserAppointmentRequestDto>> fetchHistory(@PathVariable final Long petId) {

		final List<AppointmentRequestStatus> statuses = List.of(appointmentRequestStatusService.findBySysname(VISITED));

		final List<UserAppointmentRequestDto> requests = appointmentRequestService
				.findAllByPetIdAndStatuses(petId, statuses)
				.stream()
				.map(req -> new UserAppointmentRequestDto(
						req.getId(), req.getClinic().getName(), req.getClinic().getName(),
						req.getVeterinarian().getSNP(), req.getAppointmentPlace(), req.getService().getName(),
						req.getStatus().getName(), req.getPet().getName(), req.getDate().toString())
				)
				.collect(toList());

		return ResponseEntity.ok(requests);
	}
}