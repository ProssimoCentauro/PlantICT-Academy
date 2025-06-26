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


    @Scheduled(cron = "0 31 11 * * *")
    //@Scheduled(fixedRate = 60000)
    @Transactional
    public void updatePlayerRankings() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime since = now.minusDays(1);

        List<MatchEntity> matches = matchRepository.findByMatchDateBetween(since.toLocalDate(), now.toLocalDate());

        for (MatchEntity match : matches) {
            Optional<PlayerEntity> optFirst = playerRepository.findById(match.getFirstPlayer().getId());
            Optional<PlayerEntity> optSecond = playerRepository.findById(match.getSecondPlayer().getId());

            if (optFirst.isEmpty() || optSecond.isEmpty()) continue;

            PlayerEntity first = optFirst.get();
            PlayerEntity second = optSecond.get();

            boolean firstWins = Math.random() < 0.5;

            if (firstWins) {
                adjustRanking(first, -1);
                adjustRanking(second, +1);
            } else {
                adjustRanking(second, -1);
                adjustRanking(first, +1);
            }
        }

        logger.info("ATP ranking update performed at {}", now);
    }

    private void adjustRanking(PlayerEntity player, int num) {
        int current = player.getRankingAtp();
        int updated = Math.max(1, current + num);

        player.setRankingAtp(updated);
        //player.setLastUpdate(LocalDateTime.now());
        playerRepository.save(player);
    }
}
