package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.ServiceCreateDto;
import ru.mashurov.rest.dto.ServiceDto;
import ru.mashurov.rest.dto.ServiceUpdateDto;
import ru.mashurov.rest.model.Clinic;
import ru.mashurov.rest.model.Service;
import ru.mashurov.rest.services.ClinicService;
import ru.mashurov.rest.services.ServiceService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ServiceController {

	private final ServiceService serviceService;

	private final ClinicService clinicService;

	@GetMapping("/services/{id}")
	public ResponseEntity<ServiceDto> findById(@PathVariable final Long id) {

		final Service service = serviceService.findById(id);

		return ResponseEntity.ok(
				new ServiceDto(service.getId(), service.getName(), service.getDescription(), service.getCost())
		);
	}

	@DeleteMapping("/services/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable final Long id) {

		serviceService.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/services/create")
	public ResponseEntity<Void> create(@RequestBody final ServiceCreateDto createDto) {

		final Clinic clinic = clinicService.findById(createDto.getClinicId());

		final Service service = new Service(
				null, createDto.getName(), createDto.getDescription(), createDto.getCost(), clinic
		);

		serviceService.save(service);

		return ResponseEntity.ok().build();
	}

	@PatchMapping("/services/update")
	public ResponseEntity<Void> update(@RequestBody final ServiceUpdateDto updateDto) {

		final Service service = serviceService.findById(updateDto.getId());

		service.setName(updateDto.getName());
		service.setDescription(updateDto.getDescription());
		service.setCost(updateDto.getCost());

		serviceService.save(service);

		return ResponseEntity.ok().build();
	}
}
