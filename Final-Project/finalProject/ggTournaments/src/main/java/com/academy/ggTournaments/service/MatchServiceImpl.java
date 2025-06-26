package com.academy.ggTournaments.service;

import com.academy.ggTournaments.dto.MatchDTO;
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
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final MatchMapper matchMapper;
    private final TournamentRepository tournamentRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);
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

        if (!matchRepository.existsById(id)) {
            logger.error("Match not found: id={}", id);
            throw new ResourceNotFoundException("Match", id);
        }

        TournamentEntity tournament = tournamentRepository
                .findById(m.getTournamentId())
                .orElseThrow(() -> {
                    logger.error("Tournament not found: id={}", m.getTournamentId());
                    return new ResourceNotFoundException("Tournament", m.getTournamentId());
                });

        PlayerEntity firstPlayer = playerRepository
                .findById(m.getFirstPlayerId())
                .orElseThrow(() -> {
                    logger.error("First player not found: id={}", m.getFirstPlayerId());
                    return new ResourceNotFoundException("First player", m.getFirstPlayerId());
                });

        PlayerEntity secondPlayer = playerRepository
                .findById(m.getSecondPlayerId())
                .orElseThrow(() -> {
                    logger.error("Second player not found: id={}", m.getSecondPlayerId());
                    return new ResourceNotFoundException("Second player", m.getSecondPlayerId());
                });

        MatchEntity updated = matchMapper.requestToEntity(m);
        updated.setOperator("system");
        updated.setId(id);
        updated.setTournament(tournament);
        updated.setFirstPlayer(firstPlayer);
        updated.setSecondPlayer(secondPlayer);

        MatchDTO dto = matchMapper.toDto(matchRepository.save(updated));
        logger.info("Match updated successfully: {}", dto);
        return dto;
    }

    @Override
    public void deleteMatch(int id) {

        if (!matchRepository.existsById(id)) {
            logger.error("Match not found with ID {}", id);
            throw new ResourceNotFoundException("Match", id);
        }
        //logger.info("Deleting match with id={}", id);
        matchRepository.deleteById(id);
        logger.info("Match deleted: id={}", id);
    }

    @Override
    public List<MatchDTO> getMatchesByTournament(int tournamentId) {
        logger.info("Fetching matches for tournament id={}", tournamentId);
        List<MatchDTO> matches = matchRepository.findByTournamentId(tournamentId)
                .stream()
                .map(matchMapper::toDto)
                .collect(Collectors.toList());
        logger.debug("Matches found: {}", matches.size());
        return matches;
    }

    @Override
    public List<MatchDTO> getMatchesByDateRange(LocalDate from, LocalDate to) {
        logger.info("Fetching matches from {} to {}", from, to);
        List<MatchDTO> matches = matchRepository.findByMatchDateBetween(from, to)
                .stream()
                .map(matchMapper::toDto)
                .collect(Collectors.toList());
        logger.debug("Matches found in date range: {}", matches.size());
        return matches;
    }
}
