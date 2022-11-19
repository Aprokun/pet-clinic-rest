package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.repositories.AppointmentRequestRepo;

@Service
@AllArgsConstructor
public class AppointmentRequestService {

    private final AppointmentRequestRepo appointmentRequestRepo;

    public AppointmentRequest create(final AppointmentRequest appointmentRequest) {
        return appointmentRequestRepo.save(appointmentRequest);
    }
}
