package ru.mashurov.rest.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mashurov.rest.model.AppointmentRequestStatus;
import ru.mashurov.rest.repositories.AppointmentRequestStatusRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentRequestStatusService {

	private final AppointmentRequestStatusRepo appointmentRequestStatusRepo;

	public AppointmentRequestStatus findBySysname(final String sysname) {

		final Optional<AppointmentRequestStatus> optionalStatus = appointmentRequestStatusRepo.findBySysname(sysname);

		if (optionalStatus.isPresent()) {
			return optionalStatus.get();
		} else {
			throw new RuntimeException("Статус с данным sysname не найден");
		}
	}
}
