package com.academy.ggTournaments.mapper;

import com.academy.ggTournaments.dto.TournamentDTO;
import com.academy.ggTournaments.entity.TournamentEntity;
import com.academy.ggTournaments.requestObject.TournamentRequestObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TournamentMapper {
    TournamentMapper INSTANCE = Mappers.getMapper(TournamentMapper.class);

    TournamentDTO toDto(TournamentEntity entity);
    TournamentEntity toEntity(TournamentDTO dto);

    TournamentEntity requestToEntity(TournamentRequestObject requestObject);
}
