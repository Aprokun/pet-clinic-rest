package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.model.AppointmentRequestStatus;
import ru.mashurov.rest.repositories.AppointmentRequestRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentRequestService {

	private final AppointmentRequestRepo appointmentRequestRepo;

	public AppointmentRequest createOrUpdate(final AppointmentRequest appointmentRequest) {
		return appointmentRequestRepo.save(appointmentRequest);
	}

	public Page<AppointmentRequest> findAllByUserIdAndStatus(
			final Long userId, final List<AppointmentRequestStatus> statuses, final Pageable pageable
	) {
		return appointmentRequestRepo.findAllByUserIdAndStatusIn(userId, statuses, pageable);
	}

	public void remove(final Long id) {
		appointmentRequestRepo.deleteById(id);
	}

	public Page<AppointmentRequest> findAllByClinicId(final Long clinicId, final Pageable pageable) {
		return appointmentRequestRepo.findAllByClinicId(clinicId, pageable);
	}

	public List<AppointmentRequest> findAllByVeterinarianIdAndDateBetween(
			final Long veterinarianId, final LocalDateTime begin, final LocalDateTime end
	) {
		return appointmentRequestRepo.findAllByVeterinarianIdAndDateBetween(veterinarianId, begin, end);
	}

	public AppointmentRequest findById(final Long requestId) {

		final Optional<AppointmentRequest> optionalAppointmentRequest = appointmentRequestRepo.findById(requestId);

		if (optionalAppointmentRequest.isPresent()) {
			return optionalAppointmentRequest.get();
		} else {
			throw new RuntimeException("Нет заявления с id - " + requestId);
		}
	}

	public Page<AppointmentRequest> findAllByStatusSysnamesAndClinicId(
			final List<String> statusSysnames, final Long clinicId, final Pageable pageable
	) {

		return appointmentRequestRepo.findAllByStatusSysnameInAndClinicId(statusSysnames, clinicId, pageable);
	}

	public void save(final AppointmentRequest appointmentRequest) {
		appointmentRequestRepo.save(appointmentRequest);
	}
}
