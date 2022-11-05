package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.pojo.AppointmentRequestJ;
import ru.mashurov.rest.repositories.AppointmentRequestRepo;

@Service
@AllArgsConstructor
public class AppointmentRequestService {

    private final AppointmentRequestRepo appointmentRequestRepo;

    public AppointmentRequestJ create(final AppointmentRequestJ appointmentRequest) {
        return appointmentRequestRepo.save(appointmentRequest);
    }
}
