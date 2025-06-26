package com.academy.ggTournaments.controller;

import com.academy.ggTournaments.dto.PlayerDTO;
import com.academy.ggTournaments.requestObject.PlayerRequestObject;
import com.academy.ggTournaments.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/add{ranking}")
    public PlayerDTO createPlayer(@PathVariable int ranking, @RequestBody PlayerRequestObject p) {
        return playerService.createPlayer(ranking, p);
    }

    @PutMapping("/{id}/{ranking}")
    public PlayerDTO updatePlayer(@PathVariable int id, @PathVariable int ranking, @RequestBody PlayerRequestObject p) {
        return playerService.updatePlayer(id, ranking, p);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayer(@PathVariable int id) {
        return playerService.getPlayer(id);
    }

    @GetMapping("/search")
    public List<PlayerDTO> searchPlayers(@RequestParam String nameOrSurname) {
        return playerService.searchPlayers(nameOrSurname);
    }

    @GetMapping("/sponsor")
    public List<PlayerDTO> searchBySponsor(@RequestParam String sponsor) {
        return playerService.searchBySponsor(sponsor);
    }

    @GetMapping("/ranking")
    public List<PlayerDTO> getRanking() {
        return playerService.getRanking();
    }
}
