package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.pojo.ClinicJ;

import java.util.Set;

public interface ClinicRepo extends CrudRepository<ClinicJ, Long> {

    Set<ClinicJ> findAllByRegionCode(final Long regionCode);
}
