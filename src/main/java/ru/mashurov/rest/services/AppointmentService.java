package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.Appointment;
import ru.mashurov.rest.repositories.AppointmentRepo;

import java.util.ArrayList;
import java.util.List;

import static ru.mashurov.rest.utils.ErrorMessages.PET_NOT_EXIST;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepo appointmentRepo;

    private final PetService petService;

    public List<Appointment> findAppointmentHistoryByPetId(final Long petId) {

        if (petService.existById(petId)) {
            return new ArrayList<>(appointmentRepo.findAppointmentHistoryByPetId(petId));
        } else {
            //TODO 404
            throw new RuntimeException(PET_NOT_EXIST);
        }
    }

}
