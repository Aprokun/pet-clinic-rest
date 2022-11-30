package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.Region;

@Repository
public interface RegionRepo extends CrudRepository<Region, Long> {
}
