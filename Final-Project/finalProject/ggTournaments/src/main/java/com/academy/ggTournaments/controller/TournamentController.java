package com.academy.ggTournaments.controller;

import com.academy.ggTournaments.dto.TournamentDTO;
import com.academy.ggTournaments.requestObject.TournamentRequestObject;
import com.academy.ggTournaments.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping
    public TournamentDTO createTournament(@RequestBody TournamentRequestObject t) {
        return tournamentService.createTournament(t);
    }

    @PutMapping("/{id}")
    public TournamentDTO updateTournament(@PathVariable int id, @RequestBody TournamentRequestObject t) {
        return tournamentService.updateTournament(id, t);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable int id) {
        tournamentService.deleteTournament(id);
    }

    @GetMapping("/{id}")
    public TournamentDTO getTournamentById(@PathVariable int id) {
        return tournamentService.getTournamentById(id);
    }

    @GetMapping("/search")
    public List<TournamentDTO> searchByLocation(@RequestParam String location) {
        return tournamentService.searchByLocation(location);
    }
}
