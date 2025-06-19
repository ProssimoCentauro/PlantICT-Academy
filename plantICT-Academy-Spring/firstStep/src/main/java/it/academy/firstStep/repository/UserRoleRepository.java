package it.academy.firstStep.repository;

import it.academy.firstStep.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Integer> {

    @Query("SELECT ur FROM UserRoleEntity ur WHERE ur.user.id = :userId AND ur.role.id = :roleId")
    Optional<UserRoleEntity> findUserRoleEntity(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
