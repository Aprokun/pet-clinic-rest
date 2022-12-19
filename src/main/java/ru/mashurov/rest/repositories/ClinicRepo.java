package ru.mashurov.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.Clinic;

public interface ClinicRepo extends CrudRepository<Clinic, Long> {

    Page<Clinic> findAllByRegionCode(final Long regionCode, final Pageable pageable);
}
