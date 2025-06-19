package it.academy.firstStep.repository;

import it.academy.firstStep.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Integer id(int id);


}
