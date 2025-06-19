import java.util.ArrayList;
import java.util.List;

public class TournamentsManager<T extends Match> {
    private List<T> tournaments = new ArrayList<>();


    TournamentsManager() {}
    TournamentsManager(T ... args) {
        for (T tournament : args) {
            tournaments.add(tournament);
        }
    }

    List<T> getTournaments() {
        return ( tournaments );
    }
    T   getTournament(int i) {
        return tournaments.get(i);
    }

    void addTournament(T tournament) {
        tournaments.add(tournament);
    }
    void removeTournament(T tournament) {
        tournaments.remove(tournament);
    }

    void clearTournaments() {
        tournaments.clear();
    }

    void printTournamentsInfo() {
        System.out.println();
        System.out.println("Tournaments: " + tournaments.size());
        for (T tournament : tournaments) {
            tournament.printResult();
            System.out.println();
        }
    }

    void printAllPlayers() {
        int i;
        System.out.println("All players: ");
        for (T tournament : tournaments) {
            System.out.println();
            i = 1;
            for (Player player : tournament.getPlayers()) {
                System.out.println(tournament.getGame() + " Match - Player number " + i +
                        " is " + player.getNickname());
                i++;
            }
        }
    }

    /*public List<Player> CreatePlayersList(V ... players) {
        List<Player> playersList = new ArrayList<>();
        for (V player : players) {
            playersList.add(player);
        }
        return (playersList);
    }*/

    /*public Match CreateTournament(String game, List<T> players, String winner, String date) {
        Match match = new Match(game, players, winner, date);
    }*/

}
