package com.academy.ggTournaments.requestObject;

import com.academy.ggTournaments.entity.BaseEntity;
import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.entity.TournamentEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MatchRequestObject {
    private Integer tournamentId;
    private Integer firstPlayerId;
    private Integer secondPlayerId;
    private LocalDate matchDate;
}
