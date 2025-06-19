package it.academy.firstStep.repository;

import it.academy.firstStep.entities.RoleEntity;
import it.academy.firstStep.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

    @Query("SELECT rr FROM RoleEntity rr WHERE rr.name = :roleName")
    Optional<RoleEntity> findRoleEntity(@Param("roleName") String roleName);

}
