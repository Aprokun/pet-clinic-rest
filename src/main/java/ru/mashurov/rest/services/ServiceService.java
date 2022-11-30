package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import ru.mashurov.rest.model.Service;
import ru.mashurov.rest.repositories.ServiceRepo;

import static ru.mashurov.rest.utils.ErrorMessages.SERVICE_NOT_EXIST;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepo serviceRepo;

    public Service findById(final Long id) {

        if (!serviceRepo.existsById(id)) {

            //TODO 404
            throw new RuntimeException(SERVICE_NOT_EXIST);
        }

        return serviceRepo.findById(id).get();

    }
}
