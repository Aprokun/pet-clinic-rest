package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.pojo.VeterinarianJ;
import ru.mashurov.rest.repositories.VeterinarianRepo;

import java.util.Set;

import static ru.mashurov.rest.utils.ErrorMessages.VETERINARIAN_NOT_EXIST;

@Service
@AllArgsConstructor
public class VeterinarianService {

    private final VeterinarianRepo veterinarianRepo;

    private final ClinicService clinicService;

    public Set<VeterinarianJ> findAllByClinicId(final Long clinicId) {
        return veterinarianRepo.findAllByClinicId(clinicId);
    }

    public VeterinarianJ findById(final Long id) {

        if (!veterinarianRepo.existsById(id)) {

            //TODO 404
            throw new RuntimeException(VETERINARIAN_NOT_EXIST);
        }

        return veterinarianRepo.findById(id).get();
    }
}
