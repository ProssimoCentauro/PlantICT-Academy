import exceptions.PlayerNotAvailableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {


    public static List<Player> createList(Player ... players) {
        List<Player> playerList = new ArrayList<>();
        for (Player player : players) {
            playerList.add(player);
        }
        return (playerList);
    }

    public static Match createMatch(String name, CompetitiveGames type, List<Player> players,
                                    Player winner, String date) {
        Match match = new Match(name, type, players, winner, date);
        //match.clearPlayers();
        /*for (Player p : players) {
            try {
                //PlayerManager player = p;
                p.join(match);
            }
            catch (PlayerNotAvailableException e) {
                System.out.println(e.getMessage());
                match.removePlayer(p);
            }
        }*/
        return (match);
    }

    List<Match> createMatches(List<Player> players, HashMap<String, CompetitiveGames> games) {
        List<ProPlayer> proPlayers = new ArrayList<>();
        List<CasualPlayer> casualPlayers = new ArrayList<>();
        List<GuestPlayer> guestPlayers = new ArrayList<>();
        List<Match> matches = new ArrayList<>();

        for (Player player : players) {
            if (player instanceof ProPlayer) {
                proPlayers.add((ProPlayer) player);
            }
        }
        for (Player player : players) {
            if (player instanceof CasualPlayer) {
                casualPlayers.add((CasualPlayer) player);
            }
        }
        for (Player player : players) {
            if (player instanceof GuestPlayer) {
                guestPlayers.add((GuestPlayer) player);
            }
        }
        Match m1 = new Match();
        matches.add()

    }

    public static void main(String[] args) {
        ProPlayer p1 = new ProPlayer("Axios", 1, 23, "RSN", "RED BULL");
        ProPlayer p2 = new ProPlayer("FaZe PINO", 2, 40, "FaZe", "RAZER");
        CasualPlayer p3 = new CasualPlayer("Drago", 3, 8, 7.4, "VALORANT");
        CasualPlayer p4 = new CasualPlayer("X_Maniac_X", 4, 10, 10.2, "CS:GO");
        GuestPlayer p5 = new GuestPlayer("Guest1", 99, 0, 2.1);
        GuestPlayer p6 = new GuestPlayer("Guest2", 100, 0, 3);

        List<Player> l1 = createList(p1, p2);
        List<Player> l2 = createList(p3, p4);
        List<Player> l3 = createList(p5, p6);
        List<Player> allPlayers = createList(p1, p2, p3, p4, p5, p6);

        HashMap<String, CompetitiveGames> games = new HashMap<>();
        games.put("CS:GO", CompetitiveGames.SHOOTER);
        games.put("LOL", CompetitiveGames.MOBA);
        games.put("Tekken", CompetitiveGames.FIGHTING);


        List<Match> matches =
        Match m1 = new Match("LOL", CompetitiveGames.SHOOTER, l1, p2, "2 July 2025");
        Match m2 = new Match("CS:GO", CompetitiveGames.MOBA, l2, p3, "13 June 2025");
        Match m3 = new Match("Tekken", CompetitiveGames.FIGHTING, l3, p6, "30 February 2025");

        m2.calculateStatistics();
        //System.out.println(p1.isAvailable);
        //return ;
        //Match m4 = new Match("Tekken", CompetitiveGames.FIGHTING, l1, p1, "30 February 2025");


        allPlayers.forEach((player -> {
            if (player.getLevel() > 10) {
                System.out.println("Player " + player.getNickname() + " is above level 10");
            }
        }));

        System.out.println();

        allPlayers.stream()
                .filter(p -> p instanceof MatchesWon)
                .map(p -> (MatchesWon) p)
                .sorted((a, b) -> Integer.compare(b.getMatchesWon(), a.getMatchesWon()))
                .forEach(p -> System.out.println("Player: " + ((Player) p).getNickname() + " Wins " + p.getMatchesWon() + " times"));


        System.out.println("\nALL MATCHES WON BY " + p2.getNickname());
        p2.getMatchesWonList().forEach((match) -> {
            match.printResult();
        });

        TournamentsManager tm = new TournamentsManager(m1, m2, m3);

        tm.printTournamentsInfo();

        tm.printAllPlayers();

      /*  for (Object match : tm.getTournaments()) {
            if ((Match) match) {}
        }*/
    }
}
