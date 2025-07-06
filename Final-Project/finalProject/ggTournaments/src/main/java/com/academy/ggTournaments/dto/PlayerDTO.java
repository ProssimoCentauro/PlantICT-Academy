package com.academy.ggTournaments.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PlayerDTO extends BaseDTO {
    private Integer id;
    private String name;
    private String surname;
    private String sponsor;
    private LocalDate birthdate;
    private int rankingAtp;
}
