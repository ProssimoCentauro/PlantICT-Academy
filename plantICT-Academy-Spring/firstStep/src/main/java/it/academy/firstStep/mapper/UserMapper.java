package it.academy.firstStep.mapper;

import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.entities.UserEntity;
import it.academy.firstStep.requestObjects.UserRequestObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(UserDto user);
    UserDto toUserDto(UserEntity user);

    List<UserEntity> toUserEntityList(List<UserDto> userDtos);
    List<UserDto> toUserDtoList(List<UserEntity> users);

    UserEntity requestToUserEntity(UserRequestObject userRequestObject);
}
