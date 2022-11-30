package ru.mashurov.rest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.Appointment;

import java.util.Set;

public interface AppointmentRepo extends CrudRepository<Appointment, Long> {

    @Query(value = "select a.id, appointment_date, appointment_place "
            + "from appointment a "
            + "join appointment_history ah on a.id = ah.appointment_id "
            + "join veterinarian v on a.veterinarian_id = v.id "
            + "join service s on a.service_id = s.id "
            + "where ah.pet_id = ?1",
            nativeQuery = true
    )
    Set<Appointment> findAppointmentHistoryByPetId(final Long petId);
}
