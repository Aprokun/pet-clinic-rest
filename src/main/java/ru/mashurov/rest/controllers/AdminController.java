package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.model.Admin;
import ru.mashurov.rest.services.AdminService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AdminController {

	private final AdminService adminService;

	@GetMapping("/admins")
	public ResponseEntity<Admin> getAdmin(@RequestParam(name = "username") final String login) {
		final Admin byLogin = adminService.findByLogin(login);
		return ResponseEntity.ok(byLogin);
	}
}
