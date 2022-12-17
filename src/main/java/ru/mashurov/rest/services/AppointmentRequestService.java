package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.repositories.AppointmentRequestRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentRequestService {

	private final AppointmentRequestRepo appointmentRequestRepo;

	public AppointmentRequest createOrUpdate(final AppointmentRequest appointmentRequest) {
		return appointmentRequestRepo.save(appointmentRequest);
	}

	public List<AppointmentRequest> findAllByUserId(final Long userId) {
		return new ArrayList<>(appointmentRequestRepo.findAllByUserId(userId));
	}

	public void remove(final Long id) {
		appointmentRequestRepo.deleteById(id);
	}

	public Page<AppointmentRequest> findAllByClinicId(final Long clinicId, final Pageable pageable) {
		return appointmentRequestRepo.findAllByClinicId(clinicId, pageable);
	}

	public AppointmentRequest findById(final Long requestId) {

		final Optional<AppointmentRequest> optionalAppointmentRequest = appointmentRequestRepo.findById(requestId);

		if (optionalAppointmentRequest.isPresent()) {
			return optionalAppointmentRequest.get();
		} else {
			throw new RuntimeException("Нет заявления с id - " + requestId);
		}
	}
}
