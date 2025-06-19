package it.academy.firstStep.mapper;

import it.academy.firstStep.dto.RoleDto;
import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.dto.UserRoleDto;
import it.academy.firstStep.entities.RoleEntity;
import it.academy.firstStep.entities.UserEntity;
import it.academy.firstStep.entities.UserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.Role;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
/*
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "role.id", source = "roleId")
    UserRoleEntity toUserRoleEntity(UserRoleDto user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "role.id", target = "roleId")
    UserRoleDto toUserRoleDto(UserRoleEntity user);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "role.id", target = "roleId")
    List<UserRoleDto> toUserRoleDtos(List<UserRoleEntity> users);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "role.id", source = "roleId")
    List<UserRoleEntity> toUserRoleEntities(List<UserRoleDto> users);
*/

    UserRoleEntity toUserRoleEntity(UserRoleDto user);
    UserRoleDto toUserRoleDto(UserRoleEntity user);
    List<UserRoleDto> toUserRoleDtos(List<UserRoleEntity> users);
    List<UserRoleEntity> toUserRoleEntities(List<UserRoleDto> users);


}

