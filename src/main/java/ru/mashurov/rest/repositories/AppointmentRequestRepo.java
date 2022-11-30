package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.AppointmentRequest;

import java.util.Set;

@Repository
public interface AppointmentRequestRepo extends CrudRepository<AppointmentRequest, Long> {

    Set<AppointmentRequest> findAllByUserId(final Long userId);

    Set<AppointmentRequest> findAllByVeterinarianId(final Long veterinarianId);
}
