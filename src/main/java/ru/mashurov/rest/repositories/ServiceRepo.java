package ru.mashurov.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mashurov.rest.model.Service;

import java.util.Set;

public interface ServiceRepo extends CrudRepository<Service, Long> {

    @Query("select x from Service x where x.clinic.id = :clinicId")
    Page<Service> findAllByClinicId(final Long clinicId, final Pageable pageable);
}
