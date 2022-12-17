package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mashurov.rest.dto.AppointmentRequestAdminDto;
import ru.mashurov.rest.model.Admin;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.model.AppointmentRequestStatus;
import ru.mashurov.rest.services.AdminService;
import ru.mashurov.rest.services.AppointmentRequestService;
import ru.mashurov.rest.services.AppointmentRequestStatusService;

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
	public ResponseEntity<Page<AppointmentRequestAdminDto>> findAll(
			@PathVariable final Long majorId,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) final Pageable pageable
	) {

		final Admin admin = adminService.findById(majorId);

		final Page<AppointmentRequestAdminDto> pageAppointmentRequests = appointmentRequestService
				.findAllByClinicId(admin.getClinic().getId(), pageable)
				.map(appointmentRequest -> new AppointmentRequestAdminDto(
						appointmentRequest.getId(), appointmentRequest.getVeterinarian().getSNP(),
						appointmentRequest.getAppointmentPlace(), appointmentRequest.getPet().getName(),
						appointmentRequest.getService().getName())
				);

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
	public ResponseEntity<Void> rejectRequestByMajor	(@PathVariable final Long requestId) {

		final AppointmentRequest appointmentRequest = appointmentRequestService.findById(requestId);

		final AppointmentRequestStatus rejectStatus = appointmentRequestStatusService.findBySysname(REJECTED);
		appointmentRequest.setStatus(rejectStatus);

		appointmentRequestService.createOrUpdate(appointmentRequest);

		return ResponseEntity.noContent().build();
	}
}
