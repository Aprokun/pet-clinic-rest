package ru.mashurov.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.AppointmentRequest;
import ru.mashurov.rest.model.AppointmentRequestStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface AppointmentRequestRepo extends CrudRepository<AppointmentRequest, Long> {

	Page<AppointmentRequest> findAllByUserIdAndStatusIn(
			final Long userId, final List<AppointmentRequestStatus> status, final Pageable pageable
	);

	Page<AppointmentRequest> findAllByVeterinarianId(final Long veterinarianId, final Pageable pageable);

	Page<AppointmentRequest> findAllByClinicId(final Long clinicId, final Pageable pageable);

	Page<AppointmentRequest> findAllByStatusSysnameInAndClinicId(
			final List<String> statusSysnames, final Long clinicId, final Pageable pageable
	);

	List<AppointmentRequest> findAllByVeterinarianIdAndDateBetween(
			final Long veterinarianId, final LocalDateTime begin, final LocalDateTime end
	);

	Page<AppointmentRequest> findAllByStatusSysnameInAndClinicIdAndDateBetween(
			final List<String> statusSysnames, final Long clinicId, final LocalDateTime begin,
			final LocalDateTime end, final Pageable pageable
	);

	Set<AppointmentRequest> findAllByPetIdAndStatusIn(final Long petId, final List<AppointmentRequestStatus> statuses);
}
