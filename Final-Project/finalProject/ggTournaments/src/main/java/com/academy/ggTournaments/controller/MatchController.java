package com.academy.ggTournaments.controller;

import com.academy.ggTournaments.dto.MatchDTO;
import com.academy.ggTournaments.requestObject.MatchRequestObject;
import com.academy.ggTournaments.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping
    public MatchDTO createMatch(@RequestBody MatchRequestObject m) {
        return matchService.createMatch(m);
    }

    @PutMapping("/{id}")
    public MatchDTO updateMatch(@PathVariable int id, @RequestBody MatchRequestObject m) {
        return matchService.updateMatch(id, m);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        matchService.deleteMatch(id);
    }

    @GetMapping("/tournament/{tournamentId}")
    public List<MatchDTO> getMatchesByTournament(@PathVariable int tournamentId) {
        return matchService.getMatchesByTournament(tournamentId);
    }

    @GetMapping("/date-range")
    public List<MatchDTO> getMatchesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return matchService.getMatchesByDateRange(from, to);
    }
}
