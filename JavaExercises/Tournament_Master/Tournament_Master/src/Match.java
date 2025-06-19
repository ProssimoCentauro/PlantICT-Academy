import exceptions.PlayerNotAvailableException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
public class Match {
    final String RESET = "\u001B[0m";

    final String RED = "\u001B[1;31m";
    final String GREEN = "\u001B[1;32m";
    final String YELLOW = "\u001B[1;33m";
    final String BLUE = "\u001B[1;34m";

    private String game;
    private List<Player> playersList = new ArrayList<>();
    private List<CasualPlayer> casualPlayers = new ArrayList<>();
    private Player winner;
    private String date;
    private CompetitiveGames gameType;

    Match(String game, CompetitiveGames gameType, List<Player> players, Player winner, String date) {
        this.gameType = gameType;
        this.game = game;
        //this.players = players;
        for (Player player : new ArrayList<>(players)) {
            if (!player.isAvailable()) {
                throw new PlayerNotAvailableException("Player " + player.getNickname() + " is not available");
            } else {
                player.join(this);
            }
        }
        for (Player player : new ArrayList<>(players)) {
            if (player instanceof CasualPlayer) {
                casualPlayers.add((CasualPlayer) player);
            }
        }
        /*for (Player player : players) {
            if (player.isAvailable() == false) {
                throw new PlayerNotAvailableException("Player" + player.getNickname() + " is not available");
            }
            else {
                player.join(this);
            }
        }*/
        this.winner = winner;
        this.date = date;
        if (winner instanceof CasualPlayer) {
            ((CasualPlayer) winner).addMatchWon(this);
        } else if (winner instanceof ProPlayer) {
            ((ProPlayer) winner).addMatchWon(this);
        }
    }

    public String getGame() {
        return game;
    }

    public List<Player> getPlayers() {
        return playersList;
    }

    public Player getWinner() {
        return winner;
    }

    public String getDate() {
        return date;
    }

    public CompetitiveGames getGameType() {
        return gameType;
    }

    public void addPlayer(Player p) {
        playersList.add(p);
    }

    public void removePlayer(Player p) {
        playersList.remove(p);
    }

    public void clearPlayers() {
        playersList.clear();
    }

    public void printResult() {
        System.out.println(YELLOW + "Game: " + RESET + game);
        System.out.println(YELLOW + "Date: " + RESET + date);
        System.out.println(YELLOW + "Players List: " + RESET);
        for (Player player : playersList) {
            System.out.println(player.getNickname());
        }
        System.out.println(GREEN + "Winner: " + RESET + winner.getNickname());
    }

    public List<CasualPlayer> getMaxLevelPlayer() {
        List<CasualPlayer> maxLevelPlayers = new ArrayList<>();
        int maxLevel = Integer.MIN_VALUE;

        for (CasualPlayer player : casualPlayers) {
            int level = player.getLevel();
            if (level > maxLevel) {
                maxLevel = level;
                maxLevelPlayers.clear();
                maxLevelPlayers.add(player);
            } else if (level == maxLevel) {
                maxLevelPlayers.add(player);
            }
        }
        return maxLevelPlayers;
    }


    public int getMaxScore() {
        int score = 0;
        for (CasualPlayer casualPlayer : casualPlayers) {
            if (casualPlayer.getStatisticsScore() > score) {
                score = casualPlayer.getStatisticsScore();
            }
        }
        return score;
    }

    public List<CasualPlayer> calculateMostLikelyPlayers(HashMap<CasualPlayer, Integer> map) {
        int max = Collections.max(map.values());
        List<CasualPlayer> players = new ArrayList<>();
        for (Map.Entry<CasualPlayer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                players.add(entry.getKey());
            }
        }
        return (players);
    }

    public void printScores(List<CasualPlayer> players) {
        for (CasualPlayer player : players) {
            System.out.println(player.getStatisticsScore());
        }
    }

    public void calculateStatistics() {
        HashMap<CasualPlayer, Integer> scores = new HashMap<>();
        for (CasualPlayer player : casualPlayers) {
            if (player.getFavouriteGame().equals(game)) {
                player.addStatisticsScore(1);
            }
            scores.put(player, player.getStatisticsScore());
        }

        List<CasualPlayer> maxLevelPlayer = getMaxLevelPlayer();
        for (CasualPlayer player : maxLevelPlayer) {
            player.addStatisticsScore(1);
            scores.put(player, player.getStatisticsScore());
        }
        List<CasualPlayer> mostLikelyPlayers = calculateMostLikelyPlayers(scores);
        System.out.println("The players with the highest chance of winning are: ");
        for (CasualPlayer player : mostLikelyPlayers) {
            System.out.println(player.getNickname() + " - StatisticScore: " + player.getStatisticsScore());
        }
        System.out.println();
    }
}
