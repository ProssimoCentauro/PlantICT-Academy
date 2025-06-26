package com.academy.ggTournaments.service;

import com.academy.ggTournaments.dto.PlayerDTO;
import com.academy.ggTournaments.requestObject.PlayerRequestObject;

import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer(int ranking, PlayerRequestObject p);
    PlayerDTO updatePlayer(int id, int ranking, PlayerRequestObject p);
    void deletePlayer(int id);
    PlayerDTO getPlayer(int id);
    List<PlayerDTO> searchPlayers(String nameOrSurname);
    List<PlayerDTO> searchBySponsor(String sponsor);
    List<PlayerDTO> getRanking(); // Sorted by ranking_atp DESC
}
