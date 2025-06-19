package it.academy.firstStep.mapper;

import it.academy.firstStep.dto.RoleDto;
import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.entities.RoleEntity;
import it.academy.firstStep.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.Role;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity toRoleEntity(RoleDto user);
    RoleDto toRoleDto(RoleEntity user);

    List<RoleEntity> toRoleEntities(List<RoleDto> users);
    List<RoleDto> toRoleDtos(List<RoleEntity> users);
}
