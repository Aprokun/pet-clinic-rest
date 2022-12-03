package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.repositories.AppointmentRequestRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentRequestService {

	private final AppointmentRequestRepo appointmentRequestRepo;

	public AppointmentRequest create(final AppointmentRequest appointmentRequest) {
		return appointmentRequestRepo.save(appointmentRequest);
	}

	public List<AppointmentRequest> findAllByUserId(final Long userId) {
		return new ArrayList<>(appointmentRequestRepo.findAllByUserId(userId));
	}

	public void remove(final Long id) {
		appointmentRequestRepo.deleteById(id);
	}
}
