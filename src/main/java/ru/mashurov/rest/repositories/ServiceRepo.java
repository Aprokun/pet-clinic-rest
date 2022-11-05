package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mashurov.rest.model.pojo.ServiceJ;

public interface ServiceRepo extends CrudRepository<ServiceJ, Long> {
}
