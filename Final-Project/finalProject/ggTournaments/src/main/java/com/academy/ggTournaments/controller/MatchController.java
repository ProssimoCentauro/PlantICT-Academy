package com.academy.ggTournaments.controller;

import com.academy.ggTournaments.dto.MatchDTO;
import com.academy.ggTournaments.requestObject.MatchRequestObject;
import com.academy.ggTournaments.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/add")
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchRequestObject m) {
        MatchDTO dto = matchService.createMatch(m);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }


    @PutMapping("/{id}")
    public MatchDTO updateMatch(@PathVariable int id, @RequestBody MatchRequestObject m) {
        return matchService.updateMatch(id, m);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        matchService.deleteMatch(id);
    }

    @GetMapping("/{id}")
    public MatchDTO getMatch(@PathVariable int id) { return matchService.getMatchById(id); }


    @GetMapping("/search")
    public List<MatchDTO> searchMatches(
            @RequestParam(required = false) Integer tournamentId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return matchService.searchMatches(tournamentId, from, to);
    }

}
