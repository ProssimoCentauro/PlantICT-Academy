package com.academy.ggTournaments.repository;

import com.academy.ggTournaments.entity.MatchEntity;
import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {
    List<MatchEntity> findByTournamentId(int tournamentId);
    List<MatchEntity> findByTournament(TournamentEntity tournament);
    List<MatchEntity> findByMatchDateAfter(LocalDate date);
    List<MatchEntity> findByMatchDateBetween(LocalDate start, LocalDate end);
    boolean existsByFirstPlayer_Id(int id);
    boolean existsBySecondPlayer_Id(int id);
    boolean existsByTournament_Id(int id);
    //Optional<MatchEntity> findByPlayer1AndPlayer2AndTournamentAndMatchDate(PlayerEntity player1, PlayerEntity player2, TournamentEntity tournament, LocalDate matchDate);

    @Query("SELECT m FROM MatchEntity m WHERE " +
            "(m.firstPlayer.id = :p1Id OR m.secondPlayer.id = :p1Id OR m.firstPlayer.id = :p2Id OR m.secondPlayer.id = :p2Id) " +
            "AND m.matchDate BETWEEN :start AND :end")
    List<MatchEntity> findMatchesWithSamePlayerWithinInterval(
            @Param("p1Id") Long p1Id,
            @Param("p2Id") Long p2Id,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);

    @Query("SELECT m FROM MatchEntity m WHERE " +
            "m.tournament.id = :tournamentId " +
            "AND m.matchDate BETWEEN :start AND :end")
    List<MatchEntity> findMatchesInSameTournamentWithinOneDay(
            @Param("tournamentId") Long tournamentId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);

}
