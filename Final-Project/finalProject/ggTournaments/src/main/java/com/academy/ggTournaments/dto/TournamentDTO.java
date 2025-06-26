package com.academy.ggTournaments.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TournamentDTO extends BaseDTO {
    private Integer id;
    private String name;
    private String location;
}
