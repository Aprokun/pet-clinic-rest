package ru.mashurov.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mashurov.rest.model.pojo.UserJ;

@Repository
public interface UserRepo extends CrudRepository<UserJ, Long> {
}
