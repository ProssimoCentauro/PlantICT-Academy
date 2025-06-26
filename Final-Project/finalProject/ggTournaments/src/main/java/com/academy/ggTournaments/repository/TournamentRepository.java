package com.academy.ggTournaments.repository;

import com.academy.ggTournaments.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Integer> {
    List<TournamentEntity> findByLocationIgnoreCaseContaining(String location);
    List<TournamentEntity> findByLocationIgnoreCase(String location);
}
