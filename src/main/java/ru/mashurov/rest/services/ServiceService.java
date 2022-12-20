package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mashurov.rest.model.Service;
import ru.mashurov.rest.repositories.ServiceRepo;

import static ru.mashurov.rest.utils.ErrorMessages.SERVICE_NOT_EXIST;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepo serviceRepo;

    public ru.mashurov.rest.model.Service findById(final Long id) {

        if (!serviceRepo.existsById(id)) {

	        //TODO 404
	        throw new RuntimeException(SERVICE_NOT_EXIST);
        }

	    return serviceRepo.findById(id).get();
    }

	public Page<ru.mashurov.rest.model.Service> findAllByClinicId(final Long clinicId, final Pageable pageable) {
		return serviceRepo.findAllByClinicId(clinicId, pageable);
	}

	public void save(final Service service) {
		serviceRepo.save(service);
	}

	public void deleteById(final Long id) {
		serviceRepo.deleteById(id);
	}
}
