package ru.mashurov.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.AppointmentRequest;

import java.util.List;

@Repository
public interface AppointmentRequestRepo extends CrudRepository<AppointmentRequest, Long> {

	Page<AppointmentRequest> findAllByUserId(final Long userId, final Pageable pageable);

	Page<AppointmentRequest> findAllByVeterinarianId(final Long veterinarianId, final Pageable pageable);

	Page<AppointmentRequest> findAllByClinicId(final Long clinicId, final Pageable pageable);

	Page<AppointmentRequest> findAllByStatusSysnameInAndClinicId(
			final List<String> statusSysnames, final Long clinicId, final Pageable pageable
	);
}
