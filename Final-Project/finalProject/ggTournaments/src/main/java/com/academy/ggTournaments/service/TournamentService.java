package com.academy.ggTournaments.service;

import com.academy.ggTournaments.dto.TournamentDTO;
import com.academy.ggTournaments.requestObject.TournamentRequestObject;

import java.util.List;

public interface TournamentService {
    TournamentDTO createTournament(TournamentRequestObject t);
    TournamentDTO updateTournament(int id, TournamentRequestObject t);
    void deleteTournament(int id);
    TournamentDTO getTournamentById(int id);
    List<TournamentDTO> searchByLocation(String location);
}
