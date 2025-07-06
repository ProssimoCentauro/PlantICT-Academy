package com.academy.ggTournaments.controller;

import com.academy.ggTournaments.dto.TournamentDTO;
import com.academy.ggTournaments.requestObject.TournamentRequestObject;
import com.academy.ggTournaments.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping("/add")
    public ResponseEntity<TournamentDTO> createTournament(@RequestBody TournamentRequestObject t) {
        TournamentDTO dto = tournamentService.createTournament(t);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
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
    public List<TournamentDTO> searchTournaments(
            @RequestParam(required = false, defaultValue = "") String location
    ) {
        return tournamentService.searchTournaments(location);
    }
}
