package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Admin;
import ru.mashurov.rest.model.Role;
import ru.mashurov.rest.repositories.AdminRepo;
import ru.mashurov.rest.repositories.RoleRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

	private final AdminRepo adminRepo;

	private final RoleRepo roleRepo;

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

	public void save(final Admin admin) {
		adminRepo.save(admin);
	}

	public Page<Admin> findAllMajors(final Pageable pageable) {

		final Optional<Role> majorRole = roleRepo.findByNameIs("ROLE_MAJOR");

		if (majorRole.isPresent()) {
			return adminRepo.findAllByRolesContains(majorRole.get(), pageable);
		} else {
			throw new RuntimeException("Роли не существует");
		}
	}

	public void removeMajor(final Long majorId) {
		adminRepo.deleteById(majorId);
	}
}
