package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.model.Clinic;
import ru.mashurov.rest.services.ClinicService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ClinicController {

	private final ClinicService clinicService;

	@GetMapping("/clinics")
	public ResponseEntity<List<Clinic>> findAllByRegion(@RequestParam("region") final Long region) {
		return ResponseEntity.ok(clinicService.findAllByRegionCode(region));
	}

	@GetMapping("/clinics/{id}/get")
	public ResponseEntity<Clinic> findById(@PathVariable final Long id) {
		return ResponseEntity.ok(clinicService.findById(id));
	}
}
