package com.academy.ggTournaments.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "match")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tournament", nullable = false)
    private TournamentEntity tournament;

    @ManyToOne
    @JoinColumn(name = "id_first_player", nullable = false)
    private PlayerEntity firstPlayer;

    @ManyToOne
    @JoinColumn(name = "id_second_player", nullable = false)
    private PlayerEntity secondPlayer;

    @Column(name = "match_date")
    private LocalDate matchDate;
}
