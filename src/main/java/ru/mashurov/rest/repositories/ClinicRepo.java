package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.Clinic;

import java.util.Set;

public interface ClinicRepo extends CrudRepository<Clinic, Long> {

    Set<Clinic> findAllByRegionCode(final Long regionCode);
}
