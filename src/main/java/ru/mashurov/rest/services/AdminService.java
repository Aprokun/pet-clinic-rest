package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Admin;
import ru.mashurov.rest.repositories.AdminRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

	private final AdminRepo adminRepo;

	public Admin findByLogin(final String login) {

		final Optional<Admin> optionalMajor = adminRepo.findByLogin(login);

		if (optionalMajor.isPresent()) {
			return optionalMajor.get();
		} else {
			throw new RuntimeException();
		}
	}

	public Admin findById(final Long id) {

		final Optional<Admin> optionalAdmin = adminRepo.findById(id);

		if (optionalAdmin.isPresent()) {
			return optionalAdmin.get();
		} else {
			throw new RuntimeException("Админ с id=" + id + " не найден");
		}
	}
}
