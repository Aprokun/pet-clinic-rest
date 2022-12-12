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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.AppointmentRequestAdminDto;
import ru.mashurov.rest.model.Admin;
import ru.mashurov.rest.services.AdminService;
import ru.mashurov.rest.services.AppointmentRequestService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AppointmentRequestAdminController {

	private final AppointmentRequestService appointmentRequestService;

	private final AdminService adminService;

	@GetMapping("/admin/{adminId}/appointments")
	public ResponseEntity<Page<AppointmentRequestAdminDto>> findAllForAdmins(
			@PathVariable final Long adminId,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) final Pageable pageable
	) {

		final Admin admin = adminService.findById(adminId);

		final Page<AppointmentRequestAdminDto> pageAppointmentRequests = appointmentRequestService
				.findAllByClinicId(admin.getClinic().getId(), pageable)
				.map(appointmentRequest -> new AppointmentRequestAdminDto(
						appointmentRequest.getId(), appointmentRequest.getVeterinarian().getSNP(),
						appointmentRequest.getAppointmentPlace(), appointmentRequest.getPet().getName(),
						appointmentRequest.getService().getName())
				);

		return ResponseEntity.ok(pageAppointmentRequests);
	}
}
