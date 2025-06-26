package com.academy.ggTournaments.service;

import com.academy.ggTournaments.dto.PlayerDTO;
import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.exception.GGException;
import com.academy.ggTournaments.exception.ResourceNotFoundException;
import com.academy.ggTournaments.mapper.PlayerMapper;
import com.academy.ggTournaments.repository.MatchRepository;
import com.academy.ggTournaments.repository.PlayerRepository;
import com.academy.ggTournaments.requestObject.PlayerRequestObject;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;
    private final MatchRepository matchRepository;
    private final PlayerMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
/*
    public PlayerServiceImpl(PlayerRepository repository, PlayerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }*/

    private int checkRanking(int ranking) {
        if (ranking < 1) {
            int maxRanking = repository.findAll().stream()
                    .mapToInt(PlayerEntity::getRankingAtp)
                    .max()
                    .orElse(0);
            logger.info("Auto-assigning new ranking: {}", maxRanking + 1);
            return maxRanking + 1;
        } else {
            final int finalRanking = ranking;
            boolean exists = repository.findAll().stream()
                    .anyMatch(player -> player.getRankingAtp() == finalRanking);

            if (exists) {
                logger.warn("Attempt to assign duplicate ranking: {}", finalRanking);
                throw new GGException("There is already a player with the same ranking.", HttpStatus.CONFLICT);
            }
        }
        return ranking;
    }

    @Override
    public PlayerDTO createPlayer(int ranking, PlayerRequestObject p) {
        LocalDate birthdate = p.getBirthdate();
        LocalDate today = LocalDate.now();

        Period age = Period.between(birthdate, today);
        if (age.getYears() < 18) {
            logger.warn("Player under 18 attempted to register: {} years old", age.getYears());
            throw new GGException("Player must be at least 18 years old.", HttpStatus.BAD_REQUEST);
        }
        ranking = checkRanking(ranking);

        PlayerEntity entity = mapper.requestToEntity(p);
        entity.setRankingAtp(ranking);
        PlayerEntity saved = repository.save(entity);
        logger.info("Created new player with ID {} and ranking {}", saved.getId(), saved.getRankingAtp());
        return mapper.toDto(saved);
    }

    @Override
    public PlayerDTO updatePlayer(int id, int ranking, PlayerRequestObject p) {

        PlayerEntity existing = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Player not found with ID {}", id);
                    return new ResourceNotFoundException("Player", id);
                });
        LocalDate birthdate = existing.getBirthdate();
        if(p.getBirthdate() != null){
            birthdate = p.getBirthdate();
        }
       /* String sponsor = existing.getSponsor();
        if (p.getSponsor() != null) {
            sponsor = p.getSponsor();
        }*/

        LocalDate today = LocalDate.now();

        Period age = Period.between(birthdate, today);
        if (age.getYears() < 18) {
            logger.warn("Attempt to update player {} with underage birthdate ({} years old)", id, age.getYears());
            throw new GGException("Player must be at least 18 years old.", HttpStatus.BAD_REQUEST);
        }

        ranking = checkRanking(ranking);


        existing.setName(p.getName());
        existing.setSurname(p.getSurname());
        existing.setSponsor(p.getSponsor());
        existing.setBirthdate(p.getBirthdate());
        existing.setRankingAtp(ranking);

        PlayerEntity updated = repository.save(existing);
        logger.info("Updated player with ID {}", updated.getId());
        return mapper.toDto(updated);
    }

    @Override
    public void deletePlayer(int id) {
        boolean hasMatches = matchRepository.existsByFirstPlayer_Id(id) || matchRepository.existsBySecondPlayer_Id(id);
        if (hasMatches) {
            logger.warn("Attempt to delete player with ID {} who still has matches", id);
            throw new GGException("Cannot delete player with ID " + id + " because they are still referenced by matches.", HttpStatus.CONFLICT);
        }

        if (!repository.existsById(id)) {
            logger.error("Player not found with ID {}", id);
            throw new ResourceNotFoundException("Player", id);
        }

        repository.deleteById(id);
        logger.info("Deleted player with ID {}", id);
    }

    @Override
    public PlayerDTO getPlayer(int id) {
        return repository.findById(id)
                .map(player -> {
                    logger.info("Retrieved player with ID {}", id);
                    return mapper.toDto(player);
                })
                .orElseThrow(() -> {
                    logger.error("Player not found with ID {}", id);
                    return new ResourceNotFoundException("Player", id);
                });
    }

    @Override
    public List<PlayerDTO> searchPlayers(String nameOrSurname) {
        logger.info("Searching players with name or surname containing '{}'", nameOrSurname);
        List<PlayerEntity> list = repository.findByNameIgnoreCaseContainingOrSurnameIgnoreCaseContaining(nameOrSurname, nameOrSurname);
        return list.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> searchBySponsor(String sponsor) {
        logger.info("Searching players with sponsor '{}'", sponsor);
        return repository.findBySponsorIgnoreCase(sponsor).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PlayerDTO> getRanking() {
        logger.info("Retrieving player ranking list");
        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(PlayerEntity::getRankingAtp).reversed())
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
