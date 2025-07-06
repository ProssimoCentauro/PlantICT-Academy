package com.academy.ggTournaments.scheduler;

import com.academy.ggTournaments.entity.MatchEntity;
import com.academy.ggTournaments.entity.PlayerEntity;
import com.academy.ggTournaments.repository.MatchRepository;
import com.academy.ggTournaments.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RankingScheduler.class);
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    @Scheduled(cron = "0 42 11 * * *")
    //@Scheduled(fixedRate = 60000)
    @Transactional
    public void updatePlayerRankings() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime since = now.minusDays(1);

        logger.info("Starting ATP ranking update at {}", now);
        List<MatchEntity> matches = matchRepository.findByMatchDateBetween(since.toLocalDate(), now.toLocalDate());
        logger.info("Found {} matches played between {} and {}", matches.size(), since.toLocalDate(), now.toLocalDate());

        for (MatchEntity match : matches) {
            Optional<PlayerEntity> optFirst = playerRepository.findById(match.getFirstPlayer().getId());
            Optional<PlayerEntity> optSecond = playerRepository.findById(match.getSecondPlayer().getId());

            if (optFirst.isEmpty() || optSecond.isEmpty()) {
                logger.warn("Missing player data for match ID {}. Skipping...", match.getId());
                continue;
            }

            PlayerEntity first = optFirst.get();
            PlayerEntity second = optSecond.get();

            logger.info("Processing match ID {}: {} vs {}", match.getId(), first.getName(), second.getName());

            boolean firstWins = Math.random() < 0.5;

            if (firstWins) {
                logger.info("{} wins against {}", first.getName(), second.getName());
                adjustRanking(first, -1);
                adjustRanking(second, +1);
            } else {
                logger.info("{} wins against {}", second.getName(), first.getName());
                adjustRanking(second, -1);
                adjustRanking(first, +1);
            }
        }

        logger.info("ATP ranking update completed at {}", LocalDateTime.now());
    }

    private void adjustRanking(PlayerEntity player, int num) {
        int current = player.getRankingAtp();
        int updated = Math.max(1, current + num);

        logger.debug("Adjusting ranking for {}: {} -> {}", player.getName(), current, updated);

        player.setRankingAtp(updated);
        playerRepository.save(player);
    }
}

