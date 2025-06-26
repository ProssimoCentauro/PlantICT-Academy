package com.academy.ggTournaments.dto;

import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.entity.TournamentEntity;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDTO extends BaseDTO {
    private Integer id;
    private TournamentEntity tournament;
    private PlayerEntity firstPlayer;
    private PlayerEntity secondPlayer;
    private LocalDate matchDate;
}
