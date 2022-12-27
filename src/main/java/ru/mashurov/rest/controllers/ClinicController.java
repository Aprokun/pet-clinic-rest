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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.ClinicCreateDto;
import ru.mashurov.rest.dto.ClinicUpdateDto;
import ru.mashurov.rest.model.Clinic;
import ru.mashurov.rest.model.Region;
import ru.mashurov.rest.model.Service;
import ru.mashurov.rest.model.Veterinarian;
import ru.mashurov.rest.services.ClinicService;
import ru.mashurov.rest.services.RegionService;
import ru.mashurov.rest.services.ServiceService;
import ru.mashurov.rest.services.VeterinarianService;

import java.util.HashSet;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ClinicController {

	private final ClinicService clinicService;

	private final RegionService regionService;

	private final ServiceService serviceService;

	private final VeterinarianService veterinarianService;

	@GetMapping("/clinics")
	public ResponseEntity<Page<Clinic>> findAllByRegion(
			@RequestParam("region") final Long region,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) final Pageable pageable
	) {
		return ResponseEntity.ok(clinicService.findAllByRegionCode(region, pageable));
	}

	@GetMapping("/clinics/{id}")
	public ResponseEntity<Clinic> findById(@PathVariable final Long id) {
		return ResponseEntity.ok(clinicService.findById(id));
	}

	@DeleteMapping("/clinics/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable final Long id) {

		clinicService.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/clinics/create")
	public ResponseEntity<Void> create(@RequestBody final ClinicCreateDto createDto) {

		final Region region = regionService.findById(createDto.getRegion().getCode());

		final Clinic clinic = new Clinic(
				null, createDto.getName(), createDto.getAddress(), new HashSet<>(), region
		);

		clinicService.save(clinic);

		return ResponseEntity.ok().build();
	}

	@PatchMapping("/clinics/update")
	public ResponseEntity<Void> update(@RequestBody final ClinicUpdateDto updateDto) {

		final Clinic clinic = clinicService.findById(updateDto.getId());
		final Region region = regionService.findById(updateDto.getRegion().getCode());

		clinic.setName(updateDto.getName());
		clinic.setAddress(updateDto.getAddress());
		clinic.setRegion(region);

		clinicService.save(clinic);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/clinics/{id}/services")
	public ResponseEntity<Page<Service>> findServices(
			@PathVariable final Long id,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) final Pageable pageable
	) {
		return ResponseEntity.ok(serviceService.findAllByClinicId(id, pageable));
	}

	@GetMapping("/clinics/{id}/veterinarians")
	public ResponseEntity<Page<Veterinarian>> findVeterinarians(
			@PathVariable final Long id,
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) final Pageable pageable
	) {
		return ResponseEntity.ok(veterinarianService.findAllByClinicId(id, pageable));
	}
}
