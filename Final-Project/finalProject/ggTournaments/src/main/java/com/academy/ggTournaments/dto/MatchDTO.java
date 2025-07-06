package com.academy.ggTournaments.dto;

import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.entity.TournamentEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MatchDTO extends BaseDTO {
    private Integer id;
    private TournamentEntity tournament;
    private PlayerDTO firstPlayer;
    private PlayerDTO secondPlayer;
    private LocalDate matchDate;
}
