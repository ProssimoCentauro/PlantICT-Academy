package com.academy.ggTournaments.service;

import com.academy.ggTournaments.dto.MatchDTO;
import com.academy.ggTournaments.dto.PlayerDTO;
import com.academy.ggTournaments.entity.MatchEntity;
import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.entity.TournamentEntity;
import com.academy.ggTournaments.exception.GGException;
import com.academy.ggTournaments.exception.InvalidMatchException;
import com.academy.ggTournaments.exception.ResourceNotFoundException;
import com.academy.ggTournaments.mapper.MatchMapper;
import com.academy.ggTournaments.repository.MatchRepository;
import com.academy.ggTournaments.repository.PlayerRepository;
import com.academy.ggTournaments.repository.TournamentRepository;
import com.academy.ggTournaments.requestObject.MatchRequestObject;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HttpCodeStatusMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    @Autowired
    private final MatchMapper matchMapper;

    @Autowired
    private final TournamentRepository tournamentRepository;

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private final MatchRepository matchRepository;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Autowired
    private final HttpCodeStatusMapper httpCodeStatusMapper;

    @Override
    public MatchDTO createMatch(MatchRequestObject request) {
        logger.info("Creating match: {}", request);
        Long p1Id = (long) request.getFirstPlayerId();
        Long p2Id = (long) request.getSecondPlayerId();
        Long tournamentId = (long) request.getTournamentId();
        LocalDate matchDate = request.getMatchDate();

        LocalDate start = matchDate.minusDays(1);
        LocalDate end = matchDate.plusDays(1);

        List<MatchEntity> existing = matchRepository.findMatchesWithSamePlayerWithinInterval(p1Id, p2Id, start, end);

        if (!existing.isEmpty()) {
            throw new GGException("There is already a match with at least one equal player within a day.", HttpStatus.CONFLICT);
        }
        List<MatchEntity> sameTournamentMatches =
                matchRepository.findMatchesInSameTournamentWithinOneDay(tournamentId, start, end);

        if (!sameTournamentMatches.isEmpty()) {
            throw new GGException("There is already a match for this tournament on the same date.", HttpStatus.CONFLICT);
        }


        if (request.getFirstPlayerId() == request.getSecondPlayerId()) {
            logger.error("Invalid match: same player ID ({}) used for both players", request.getFirstPlayerId());
            throw new InvalidMatchException("A player cannot play against themselves.");
        }

        TournamentEntity tournament = tournamentRepository
                .findById(request.getTournamentId())
                .orElseThrow(() -> {
                    logger.error("Tournament not found: id={}", request.getTournamentId());
                    return new ResourceNotFoundException("Tournament", request.getTournamentId());
                });

        PlayerEntity firstPlayer = playerRepository
                .findById(request.getFirstPlayerId())
                .orElseThrow(() -> {
                    logger.error("First player not found: id={}", request.getFirstPlayerId());
                    return new ResourceNotFoundException("First player", request.getFirstPlayerId());
                });

        PlayerEntity secondPlayer = playerRepository
                .findById(request.getSecondPlayerId())
                .orElseThrow(() -> {
                    logger.error("Second player not found: id={}", request.getSecondPlayerId());
                    return new ResourceNotFoundException("Second player", request.getSecondPlayerId());
                });

        MatchEntity entity = matchMapper.requestToEntity(request);
        entity.setOperator("system");
        entity.setTournament(tournament);
        entity.setFirstPlayer(firstPlayer);
        entity.setSecondPlayer(secondPlayer);

        MatchDTO dto = matchMapper.toDto(matchRepository.save(entity));
        logger.info("Match created successfully: {}", dto);
        return dto;
    }

    @Override
    public MatchDTO updateMatch(int id, MatchRequestObject m) {
        logger.info("Updating match with id={}", id);

        MatchEntity existing = matchRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Match not found: id={}", id);
                    return new ResourceNotFoundException("Match", id);
                });

        Long p1Id = (long) m.getFirstPlayerId();
        Long p2Id = (long) m.getSecondPlayerId();
        Long tournamentId = (long) m.getTournamentId();
        LocalDate matchDate = m.getMatchDate();
        LocalDate start = matchDate.minusDays(1);
        LocalDate end = matchDate.plusDays(1);

        List<MatchEntity> list = matchRepository.findMatchesWithSamePlayerWithinInterval(p1Id, p2Id, start, end);

        if (!list.isEmpty()) {
            throw new GGException("There is already a match with at least one equal player within a day.", HttpStatus.CONFLICT);
        }
        List<MatchEntity> sameTournamentMatches =
                matchRepository.findMatchesInSameTournamentWithinOneDay(tournamentId, start, end);

        if (!sameTournamentMatches.isEmpty()) {
            throw new GGException("There is already a match for this tournament on the same date.", HttpStatus.CONFLICT);
        }


        if (m.getFirstPlayerId() == m.getSecondPlayerId()) {
            logger.error("Invalid match: same player ID ({}) used for both players", m.getFirstPlayerId());
            throw new InvalidMatchException("A player cannot play against themselves.");
        }

        if (m.getTournamentId() != null) {
            TournamentEntity tournament = tournamentRepository.findById(m.getTournamentId())
                    .orElseThrow(() -> {
                        logger.error("Tournament not found: id={}", m.getTournamentId());
                        return new ResourceNotFoundException("Tournament", m.getTournamentId());
                    });
            existing.setTournament(tournament);
        }

        if (m.getFirstPlayerId() != null) {
            PlayerEntity firstPlayer = playerRepository.findById(m.getFirstPlayerId())
                    .orElseThrow(() -> {
                        logger.error("First player not found: id={}", m.getFirstPlayerId());
                        return new ResourceNotFoundException("First player", m.getFirstPlayerId());
                    });
            existing.setFirstPlayer(firstPlayer);
        }

        if (m.getSecondPlayerId() != null) {
            PlayerEntity secondPlayer = playerRepository.findById(m.getSecondPlayerId())
                    .orElseThrow(() -> {
                        logger.error("Second player not found: id={}", m.getSecondPlayerId());
                        return new ResourceNotFoundException("Second player", m.getSecondPlayerId());
                    });
            existing.setSecondPlayer(secondPlayer);
        }

        if (m.getMatchDate() != null) {
            existing.setMatchDate(m.getMatchDate());
        }

        existing.setOperator("system");

        MatchEntity updated = matchRepository.save(existing);
        MatchDTO dto = matchMapper.toDto(updated);
        logger.info("Match updated successfully: {}", dto);
        return dto;
    }


    @Override
    public void deleteMatch(int id) {
        logger.info("Deleting match with id={}", id);
        if (!matchRepository.existsById(id)) {
            logger.error("Match not found with ID {}", id);
            throw new ResourceNotFoundException("Match", id);
        }
        //logger.info("Deleting match with id={}", id);
        matchRepository.deleteById(id);
        logger.info("Match deleted: id={}", id);
    }

    public MatchDTO getMatchById(int id) {
        logger.info("Fetching match with id={}", id);
        return matchRepository.findById(id)
                .map(player -> {
                    logger.info("Retrieved Match with ID {}", id);
                    return matchMapper.toDto(player);
                })
                .orElseThrow(() -> {
                    logger.error("Match not found with ID {}", id);
                    return new ResourceNotFoundException("Player", id);
                });
    }

    @Override
    public List<MatchDTO> searchMatches(Integer tournamentId, LocalDate from, LocalDate to) {
        logger.info("Searching matches with filters: tournamentId={}, from={}, to={}", tournamentId, from, to);

        List<MatchEntity> allMatches = matchRepository.findAll();

        List<MatchDTO> result = allMatches.stream()
                .filter(match -> {
                    boolean matches = true;

                    if (tournamentId != null && tournamentId > 0) {
                        matches = matches && match.getTournament().getId() == tournamentId;
                    }

                    if (from != null) {
                        matches = matches && !match.getMatchDate().isBefore(from);
                    }

                    if (to != null) {
                        matches = matches && !match.getMatchDate().isAfter(to);
                    }

                    return matches;
                })
                .map(matchMapper::toDto)
                .collect(Collectors.toList());

        logger.debug("Matches found after filtering: {}", result.size());
        return result;
    }

}
