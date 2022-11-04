package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.pojo.AppointmentRequestJ;

import java.util.Set;

public interface AppointmentRequestRepo extends CrudRepository<AppointmentRequestJ, Long> {

    Set<AppointmentRequestJ> findAllByUserId(final Long userId);

    Set<AppointmentRequestJ> findAllByVeterinarianId(final Long veterinarianId);
}
