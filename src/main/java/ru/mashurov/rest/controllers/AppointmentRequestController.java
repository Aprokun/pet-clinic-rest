package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.AppointmentRequestCreateDto;
import ru.mashurov.rest.dto.AppointmentRequestDto;
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
import java.util.stream.Collectors;

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
    public ResponseEntity<AppointmentRequest> create(
            @RequestBody final AppointmentRequestCreateDto dto
    ) {

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
			    null, dto.getAppointmentPlace(), clinic, clinicService, veterinarian, userPet, user, status
	    );

	    return ResponseEntity.ok(appointmentRequestService.createOrUpdate(appointmentRequest));
    }

	@GetMapping("/user/{userId}/appointments")
	public ResponseEntity<List<AppointmentRequestDto>> findAll(@PathVariable final Long userId) {

		final List<AppointmentRequestDto> appointmentRequests = appointmentRequestService
				.findAllByUserId(userId)
				.stream().map(req -> new AppointmentRequestDto(
						req.getId(), req.getClinic().getName(), req.getVeterinarian().getSNP(),
						req.getAppointmentPlace(), req.getPet().getName(), req.getService().getName()
				))
				.collect(Collectors.toList());

		return ResponseEntity.ok(appointmentRequests);
    }

    @DeleteMapping("/appointments/{id}/remove")
    public ResponseEntity<Void> remove(@PathVariable final Long id) {

        appointmentRequestService.remove(id);

        return ResponseEntity.noContent().build();
    }
}
