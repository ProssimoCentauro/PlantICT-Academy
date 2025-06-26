package com.academy.ggTournaments.mapper;

import com.academy.ggTournaments.dto.MatchDTO;
import com.academy.ggTournaments.entity.MatchEntity;
import com.academy.ggTournaments.requestObject.MatchRequestObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    //MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

   /* @Mapping(source = "tournament.id", target = "tournamentId")
    @Mapping(source = "firstPlayer.id", target = "firstPlayerId")
    @Mapping(source = "secondPlayer.id", target = "secondPlayerId")*/
    MatchDTO toDto(MatchEntity entity);

   /* @Mapping(source = "tournamentId", target = "tournament.id")
    @Mapping(source = "firstPlayerId", target = "firstPlayer.id")
    @Mapping(source = "secondPlayerId", target = "secondPlayer.id")*/
    MatchEntity toEntity(MatchDTO dto);

   /* @Mapping(source = "tournamentId", target = "tournament.id")
    @Mapping(source = "firstPlayerId", target = "firstPlayer.id")
    @Mapping(source = "secondPlayerId", target = "secondPlayer.id")
    MatchEntity requestToEntity(MatchRequestObject requestObject);*/

   @Mapping(target = "tournament", ignore = true)
   @Mapping(target = "firstPlayer", ignore = true)
   @Mapping(target = "secondPlayer", ignore = true)
   MatchEntity requestToEntity(MatchRequestObject requestObject);
}
