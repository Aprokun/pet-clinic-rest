package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Clinic;
import ru.mashurov.rest.repositories.ClinicRepo;

import static ru.mashurov.rest.utils.ErrorMessages.CLINIC_NOT_EXIST;

@Service
@AllArgsConstructor
public class ClinicService {

    private final ClinicRepo clinicRepo;

    public boolean existById(final Long id) {
        return clinicRepo.existsById(id);
    }

    public Page<Clinic> findAllByRegionCode(final Long regionCode, final Pageable pageable) {
        return clinicRepo.findAllByRegionCode(regionCode, pageable);
    }

    public Clinic findById(final Long id) {

        if (!existById(id)) {
            //TODO 404
            throw new RuntimeException(CLINIC_NOT_EXIST);
        }

        return clinicRepo.findById(id).get();
    }

    public void save(final Clinic clinic) {
        clinicRepo.save(clinic);
    }

    public void deleteById(final Long id) {
        clinicRepo.deleteById(id);
    }
}
