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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.AdminCreateDto;
import ru.mashurov.rest.model.Admin;
import ru.mashurov.rest.model.Clinic;
import ru.mashurov.rest.model.Role;
import ru.mashurov.rest.services.AdminService;
import ru.mashurov.rest.services.ClinicService;

import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AdminController {

	private final AdminService adminService;

	private final ClinicService clinicService;

	@GetMapping("/admins")
	public ResponseEntity<Admin> findAdmin(@RequestParam(name = "username") final String login) {
		return ResponseEntity.ok(adminService.findByLogin(login));
	}

	@GetMapping("/majors/{id}")
	public ResponseEntity<Admin> findMajorById(@PathVariable final Long id) {
		return ResponseEntity.ok(adminService.findById(id));
	}

	@GetMapping("/majors")
	public ResponseEntity<Page<Admin>> findAllMajors(
			@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) final Pageable pageable
	) {
		return ResponseEntity.ok(adminService.findAllMajors(pageable));
	}

	@GetMapping("/majors/{id}/remove")
	public ResponseEntity<Void> removeMajor(@PathVariable final Long id) {

		adminService.removeMajor(id);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/majors/create")
	public ResponseEntity<Void> createOrUpdateMajor(@RequestBody final AdminCreateDto adminDto) {

		final Clinic clinic = clinicService.findById(adminDto.getClinicId());

		final Role roleMajor = new Role(2L, "ROLE_MAJOR");

		final Admin admin = new Admin(
				adminDto.getId(), adminDto.getLogin(), adminDto.getPassword(), clinic, Set.of(roleMajor)
		);

		adminService.save(admin);

		return ResponseEntity.ok().build();
	}
}
