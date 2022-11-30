package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.model.Veterinarian;
import ru.mashurov.rest.services.VeterinarianService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class VeterinarianController {

	private final VeterinarianService veterinarianService;

	@GetMapping("/veterinarians")
	public ResponseEntity<List<Veterinarian>> findAllByClinicId(
			@RequestParam final Long clinic
	) {
		return ResponseEntity.ok(veterinarianService.findAllByClinicId(clinic));
	}
}
