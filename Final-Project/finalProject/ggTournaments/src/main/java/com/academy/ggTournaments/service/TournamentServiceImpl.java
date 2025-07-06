package com.academy.ggTournaments.service;

import com.academy.ggTournaments.dto.TournamentDTO;
import com.academy.ggTournaments.entity.TournamentEntity;
import com.academy.ggTournaments.exception.GGException;
import com.academy.ggTournaments.exception.ResourceNotFoundException;
import com.academy.ggTournaments.mapper.TournamentMapper;
import com.academy.ggTournaments.repository.MatchRepository;
import com.academy.ggTournaments.repository.TournamentRepository;
import com.academy.ggTournaments.requestObject.TournamentRequestObject;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private final TournamentRepository tournamentRepository;

    @Autowired
    private final MatchRepository matchRepository;

    @Autowired
    private final TournamentMapper tournamentMapper;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(TournamentServiceImpl.class);

    @Override
    public TournamentDTO createTournament(TournamentRequestObject t) {
        logger.info("Creating new tournament with data: {}", t);
        TournamentEntity saved = tournamentRepository.save(tournamentMapper.requestToEntity(t));
        logger.info("Tournament created with ID: {}", saved.getId());
        return tournamentMapper.toDto(saved);
    }

    @Override
    public TournamentDTO updateTournament(int id, TournamentRequestObject t) {
        logger.info("Updating tournament with ID: {}", id);

        TournamentEntity existing = tournamentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tournament with ID {} not found for update", id);
                    return new ResourceNotFoundException("Tournament", id);
                });

        if (t.getName() != null) {
            existing.setName(t.getName());
        }
        if (t.getLocation() != null) {
            existing.setLocation(t.getLocation());
        }

        TournamentEntity saved = tournamentRepository.save(existing);
        logger.info("Tournament with ID {} updated successfully", id);
        return tournamentMapper.toDto(saved);
    }


    @Override
    public void deleteTournament(int id) {
        boolean hasMatches = matchRepository.existsByFirstPlayer_Id(id) || matchRepository.existsBySecondPlayer_Id(id);
        if (hasMatches) {
            logger.warn("Attempt to delete Tournament with ID {} who still has matches", id);
            throw new GGException("Cannot delete Tournament with ID " + id + " because they are still referenced by matches.", HttpStatus.CONFLICT);
        }

        if (!tournamentRepository.existsById(id)) {
            logger.error("Tournament not found with ID {}", id);
            throw new ResourceNotFoundException("Tournament", id);
        }
        //logger.info("Deleting tournament with ID: {}", id);
        tournamentRepository.deleteById(id);
        logger.info("Tournament with ID {} deleted", id);
    }

    @Override
    public TournamentDTO getTournamentById(int id) {
        logger.info("Retrieving tournament with ID: {}", id);
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tournament with ID {} not found", id);
                    return new ResourceNotFoundException("Tournament", id);
                });
        logger.info("Tournament with ID {} retrieved successfully", id);
        return tournamentMapper.toDto(entity);
    }

    @Override
    public List<TournamentDTO> searchTournaments(String location) {
        logger.info("Searching tournaments by location containing: '{}'", location);

        if (location == null || location.isBlank()) {
            logger.info("Location filter is empty, returning all tournaments");
            List<TournamentEntity> all = tournamentRepository.findAll();
            return all.stream().map(tournamentMapper::toDto).collect(Collectors.toList());
        }

        List<TournamentEntity> list = tournamentRepository.findByLocationIgnoreCaseContaining(location);

        logger.info("Found {} tournaments matching location '{}'", list.size(), location);

        logger.debug("Tournaments found after filtering: {}", list.size());
        return list.stream().map(tournamentMapper::toDto).collect(Collectors.toList());
    }
}
