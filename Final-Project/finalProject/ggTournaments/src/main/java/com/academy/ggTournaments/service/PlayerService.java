package com.academy.ggTournaments.service;

import com.academy.ggTournaments.dto.PlayerDTO;
import com.academy.ggTournaments.requestObject.PlayerRequestObject;

import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer(PlayerRequestObject p);
    PlayerDTO updatePlayer(int id, PlayerRequestObject p);
    void deletePlayer(int id);
    PlayerDTO getPlayerById(int id);
    List<PlayerDTO> getRanking(); // Sorted by ranking_atp DESC
    List<PlayerDTO> searchPlayers(String name, String surname, String sponsor);
}
