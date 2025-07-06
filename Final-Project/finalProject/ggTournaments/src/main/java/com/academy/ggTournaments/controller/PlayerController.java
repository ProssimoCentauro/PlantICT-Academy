package com.academy.ggTournaments.controller;

import com.academy.ggTournaments.dto.PlayerDTO;
import com.academy.ggTournaments.requestObject.PlayerRequestObject;
import com.academy.ggTournaments.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerRequestObject p) {
        PlayerDTO dto = playerService.createPlayer(p);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }


    @PutMapping("/{id}")
    public PlayerDTO updatePlayer(@PathVariable int id, @RequestBody PlayerRequestObject p) {
        return playerService.updatePlayer(id, p);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayer(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/search")
    public List<PlayerDTO> searchPlayers(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String surname,
            @RequestParam(required = false, defaultValue = "") String sponsor
    ) {
        return playerService.searchPlayers(name, surname, sponsor);
    }

    @GetMapping("/ranking")
    public List<PlayerDTO> getRanking() {
        return playerService.getRanking();
    }
}
