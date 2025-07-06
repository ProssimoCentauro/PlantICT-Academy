package com.academy.ggTournaments.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TournamentDTO extends BaseDTO {
    private Integer id;
    private String name;
    private String location;
}
