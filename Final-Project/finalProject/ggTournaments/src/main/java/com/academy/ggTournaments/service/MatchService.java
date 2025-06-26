package com.academy.ggTournaments.service;

import com.academy.ggTournaments.dto.MatchDTO;
import com.academy.ggTournaments.requestObject.MatchRequestObject;

import java.time.LocalDate;
import java.util.List;

public interface MatchService {
    MatchDTO createMatch(MatchRequestObject m);
    MatchDTO updateMatch(int id, MatchRequestObject m);
    void deleteMatch(int id);
    List<MatchDTO> getMatchesByTournament(int tournamentId);
    List<MatchDTO> getMatchesByDateRange(LocalDate from, LocalDate to);
}
