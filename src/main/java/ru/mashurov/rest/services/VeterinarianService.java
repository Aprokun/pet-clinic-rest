package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Veterinarian;
import ru.mashurov.rest.repositories.VeterinarianRepo;

import static ru.mashurov.rest.utils.ErrorMessages.VETERINARIAN_NOT_EXIST;

@Service
@AllArgsConstructor
public class VeterinarianService {

    private final VeterinarianRepo veterinarianRepo;

	public Veterinarian findById(final Long id) {

		if (!veterinarianRepo.existsById(id)) {

			//TODO 404
			throw new RuntimeException(VETERINARIAN_NOT_EXIST);
		}

		return veterinarianRepo.findById(id).get();
	}

	public Page<Veterinarian> findAllByClinicId(final Long clinicId, final Pageable pageable) {
		return veterinarianRepo.findAllByClinicId(clinicId, pageable);
	}

	public void save(final Veterinarian veterinarian) {
		veterinarianRepo.save(veterinarian);
	}

	public void deleteById(final Long id) {
		veterinarianRepo.deleteById(id);
	}
}
