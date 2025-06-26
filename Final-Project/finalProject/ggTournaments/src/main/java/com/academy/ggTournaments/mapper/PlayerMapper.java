package com.academy.ggTournaments.mapper;

import com.academy.ggTournaments.dto.PlayerDTO;
import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.requestObject.PlayerRequestObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    //PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDTO toDto(PlayerEntity entity);
    PlayerEntity toEntity(PlayerDTO dto);

    PlayerEntity requestToEntity(PlayerRequestObject requestObject);
}
