package ru.mashurov.rest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.pojo.AppointmentJ;

import java.util.Set;

public interface AppointmentRepo extends CrudRepository<AppointmentJ, Long> {

    @Query(value = "select a.id, appointment_date, appointment_place, service_id, veterinarian_id "
            + "from appointment a "
            + "join appointment_history ah on a.id = ah.appointment_id "
            + "where ah.pet_id = ?1",
            nativeQuery = true
    )
    Set<AppointmentJ> findAppointmentHistoryByPetId(final Long petId);
}
