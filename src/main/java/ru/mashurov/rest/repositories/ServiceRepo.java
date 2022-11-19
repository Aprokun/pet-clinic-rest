package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.Service;

public interface ServiceRepo extends CrudRepository<Service, Long> {
}
