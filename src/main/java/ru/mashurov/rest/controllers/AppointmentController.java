package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.model.Appointment;
import ru.mashurov.rest.services.AppointmentService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AppointmentController {

	private final AppointmentService appointmentService;

	@GetMapping("/appointments/{petId}/history")
	public ResponseEntity<List<Appointment>> fetchHistory(@PathVariable final Long petId) {
		return ResponseEntity.ok(appointmentService.findAppointmentHistoryByPetId(petId));
	}

}