package com.academy.ggTournaments.dto;

import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.entity.TournamentEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseDTO {
    private LocalDateTime insertDate;
    private LocalDateTime lastUpdateDate;
    private String operator = "system";

}