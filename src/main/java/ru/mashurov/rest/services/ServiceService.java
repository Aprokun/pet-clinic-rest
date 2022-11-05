package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.pojo.ServiceJ;
import ru.mashurov.rest.repositories.ServiceRepo;

import static ru.mashurov.rest.utils.ErrorMessages.SERVICE_NOT_EXIST;

@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepo serviceRepo;

    public ServiceJ findById(final Long id) {

        if (!serviceRepo.existsById(id)) {

            //TODO 404
            throw new RuntimeException(SERVICE_NOT_EXIST);
        }

        return serviceRepo.findById(id).get();

    }
}
