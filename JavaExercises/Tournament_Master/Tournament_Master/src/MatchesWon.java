import java.util.List;

public interface MatchesWon {
    int getMatchesWon();
    List<Match> getMatchesWonList();
    void addMatchWon(Match match);
}
