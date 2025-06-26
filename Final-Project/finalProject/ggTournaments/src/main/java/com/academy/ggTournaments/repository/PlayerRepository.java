package com.academy.ggTournaments.repository;

import com.academy.ggTournaments.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
    List<PlayerEntity> findByNameIgnoreCaseContainingOrSurnameIgnoreCaseContaining(String name, String surname);
    List<PlayerEntity> findBySponsorIgnoreCase(String sponsor);
}
