package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.AppointmentRequestCreateDto;
import ru.mashurov.rest.dto.UserAppointmentRequestDto;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.model.AppointmentRequestStatus;
import ru.mashurov.rest.model.Clinic;
import ru.mashurov.rest.model.Pet;
import ru.mashurov.rest.model.Service;
import ru.mashurov.rest.model.User;
import ru.mashurov.rest.model.Veterinarian;
import ru.mashurov.rest.services.AppointmentRequestService;
import ru.mashurov.rest.services.AppointmentRequestStatusService;
import ru.mashurov.rest.services.ClinicService;
import ru.mashurov.rest.services.VeterinarianService;
import ru.mashurov.rest.utils.CheckHelper;

import java.util.List;
import java.util.Objects;

import static ru.mashurov.rest.values.AppointmentRequestStatusValues.ACCEPT;
import static ru.mashurov.rest.values.AppointmentRequestStatusValues.CANCELED;
import static ru.mashurov.rest.values.AppointmentRequestStatusValues.REJECTED;
import static ru.mashurov.rest.values.AppointmentRequestStatusValues.UNHANDLED;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AppointmentRequestController {

    private final AppointmentRequestService appointmentRequestService;
    private final CheckHelper checkHelper;
    private final VeterinarianService veterinarianService;
    private final ClinicService clinicService;
    private final AppointmentRequestStatusService appointmentRequestStatusService;

	@PostMapping("/appointments/create")
	public ResponseEntity<AppointmentRequest> create(@RequestBody final AppointmentRequestCreateDto dto) {

		final User user = checkHelper.checkUser(dto.getUserId());

		final Pet userPet = user
				.getPets()
				.stream()
				.filter(pet -> Objects.equals(pet.getId(), dto.getPetId()))
				.findFirst()
				.get();

        final Clinic clinic = clinicService.findById(dto.getClinicId());

        final Service clinicService = clinic
                .getServices()
                .stream()
                .filter(service -> Objects.equals(service.getId(), dto.getServiceId()))
                .findFirst()
                .get();

        final Veterinarian veterinarian = veterinarianService.findById(dto.getVeterinarianId());

	    final AppointmentRequestStatus status = appointmentRequestStatusService.findBySysname("unhandled");

	    final AppointmentRequest appointmentRequest = new AppointmentRequest(
			    null, dto.getAppointmentPlace(), dto.getDate(), clinic,
			    clinicService, veterinarian, userPet, user, status
	    );

	    return ResponseEntity.ok(appointmentRequestService.createOrUpdate(appointmentRequest));
    }

	@GetMapping("/user/{userId}/appointment-requests")
	public ResponseEntity<Page<UserAppointmentRequestDto>> findAll(
			@PathVariable final Long userId,
			@PageableDefault(sort = { "date" }, direction = Sort.Direction.DESC) final Pageable pageable
	) {

		final AppointmentRequestStatus unhandled = appointmentRequestStatusService.findBySysname(UNHANDLED);
		final AppointmentRequestStatus accept = appointmentRequestStatusService.findBySysname(ACCEPT);
		final AppointmentRequestStatus rejected = appointmentRequestStatusService.findBySysname(REJECTED);


		final List<AppointmentRequestStatus> statuses = List.of(unhandled, accept, rejected);

		final Page<UserAppointmentRequestDto> appointmentRequests = appointmentRequestService
				.findAllByUserIdAndStatusesIn(userId, statuses, pageable)
				.map(req -> new UserAppointmentRequestDto(
						req.getId(), req.getClinic().getName(), req.getClinic().getAddress(),
						req.getVeterinarian().getSNP(), req.getAppointmentPlace(), req.getService().getName(),
						req.getStatus().getName(), req.getPet().getName(), req.getDate().toString()
				));

		return ResponseEntity.ok(appointmentRequests);
	}

	@DeleteMapping("/appointments/{id}/remove")
	public ResponseEntity<Void> remove(@PathVariable final Long id) {

		appointmentRequestService.remove(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/user/{userId}/appointment-request/{reqId}/cancel")
	public ResponseEntity<Void> cancelRequest(
			@PathVariable final Long userId, @PathVariable final Long reqId
	) {

		final AppointmentRequest appointmentRequest = appointmentRequestService.findById(reqId);
		final AppointmentRequestStatus canceled = appointmentRequestStatusService.findBySysname(CANCELED);

		appointmentRequest.setStatus(canceled);

		appointmentRequestService.save(appointmentRequest);

		return ResponseEntity.ok().build();
	}
}
