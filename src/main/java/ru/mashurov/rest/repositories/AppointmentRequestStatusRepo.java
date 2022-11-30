package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.AppointmentRequestStatus;

import java.util.Optional;

@Repository
public interface AppointmentRequestStatusRepo extends CrudRepository<AppointmentRequestStatus, Long> {

	Optional<AppointmentRequestStatus> findBySysname(final String sysname);
}
