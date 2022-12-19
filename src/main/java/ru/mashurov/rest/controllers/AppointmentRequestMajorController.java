package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.MajorAppointmentRequestDto;
import ru.mashurov.rest.model.Admin;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.model.AppointmentRequestStatus;
import ru.mashurov.rest.services.AdminService;
import ru.mashurov.rest.services.AppointmentRequestService;
import ru.mashurov.rest.services.AppointmentRequestStatusService;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.mashurov.rest.values.AppointmentRequestPlaces.CLINIC;
import static ru.mashurov.rest.values.AppointmentRequestStatusValues.ACCEPT;
import static ru.mashurov.rest.values.AppointmentRequestStatusValues.REJECTED;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/major")
public class AppointmentRequestMajorController {

	private final AppointmentRequestStatusService appointmentRequestStatusService;

	private final AppointmentRequestService appointmentRequestService;

	private final AdminService adminService;

	@GetMapping("/{majorId}/appointments")
	public ResponseEntity<Page<MajorAppointmentRequestDto>> findAll(
			@PathVariable final Long majorId,
			@RequestParam final List<String> statuses,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) final Pageable pageable
	) {

		final Admin admin = adminService.findById(majorId);

		final Page<MajorAppointmentRequestDto> pageAppointmentRequests = appointmentRequestService
				.findAllByStatusSysnamesAndClinicId(statuses, admin.getClinic().getId(), pageable)
				.map(req -> new MajorAppointmentRequestDto(
						req.getId(), req.getVeterinarian().getSNP(),
						req.getAppointmentPlace().equals(CLINIC) ? "В клинике" : "На дому",
						req.getPet().getName(), req.getService().getName(), req.getStatus().getName(),
						req.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"))
				));

		return ResponseEntity.ok(pageAppointmentRequests);
	}

	@PostMapping("/requests/{requestId}/approve")
	public ResponseEntity<Void> approveRequestByMajor(@PathVariable final Long requestId) {

		final AppointmentRequest appointmentRequest = appointmentRequestService.findById(requestId);

		final AppointmentRequestStatus acceptStatus = appointmentRequestStatusService.findBySysname(ACCEPT);
		appointmentRequest.setStatus(acceptStatus);

		appointmentRequestService.createOrUpdate(appointmentRequest);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/requests/{requestId}/reject")
	public ResponseEntity<Void> rejectRequestByMajor(@PathVariable final Long requestId) {

		final AppointmentRequest appointmentRequest = appointmentRequestService.findById(requestId);

		final AppointmentRequestStatus rejectStatus = appointmentRequestStatusService.findBySysname(REJECTED);
		appointmentRequest.setStatus(rejectStatus);

		appointmentRequestService.createOrUpdate(appointmentRequest);

		return ResponseEntity.noContent().build();
	}
}
