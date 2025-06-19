import exceptions.PlayerNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public class CasualPlayer extends Player implements PlayerManager, MatchesWon{
    double WeeklyGameHours;
    String FavouriteGame;
    int statisticScore;
    boolean isAvailable = true;
    int matchesWon = 0;
    List<Match> matchesWonList = new ArrayList<>();
    PlayerType playerType = PlayerType.CASUAL;


    CasualPlayer(String nickname, int id, int level, double hours, String game) {
        super(nickname, id, level);
        this.WeeklyGameHours = hours;
        this.FavouriteGame = game;
    }

    PlayerType getPlayerType() {
        return playerType;
    }

    public int getStatisticsScore() {
        return statisticScore;
    }
    public void setStatisticsScore(int statisticScore) {
        this.statisticScore = statisticScore;
    }
    public void addStatisticsScore(int statisticScore) {
        this.statisticScore += statisticScore;
    }

    public String getFavouriteGame() {
        return FavouriteGame;
    }


    @Override
    public int getMatchesWon() {
        return (matchesWon);
    }
    @Override
    public List<Match> getMatchesWonList() {
        return matchesWonList;
    }
    @Override
    public void addMatchWon(Match match) {
        matchesWonList.add(match);
        matchesWon++;
    }

    @Override
    public void description(String description) {
        System.out.println("Player name:" + super.getNickname());
        System.out.println("Player id:" + super.getId());
        System.out.println("Player level:" + super.getLevel());
        System.out.println("Player type: Casual Player");
        System.out.println(super.getNickname() + " weekly fame hours: " + WeeklyGameHours);
        System.out.println(super.getNickname() + " Player Favourite game: " + FavouriteGame);
        System.out.println("About: " + super.getNickname() + " " + description);
    }

    @Override
    public void join(Match match) {
        if (this.isAvailable) {
            match.addPlayer(this);
            this.isAvailable = false;
        }
        else {
            throw new PlayerNotAvailableException(RED + "PLAYER" + RESET + this.getNickname() +
                    RED + "NOT AVAILABLE!" + RESET);
        }
    }

    @Override
    public void leave(Match match) {
        match.removePlayer(this);
        this.isAvailable = true;
    }

    @Override
    public boolean isAvailable() {
        return ( this.isAvailable );
    }

    public void changeAvailable() {
        if (this.isAvailable) {
            this.isAvailable = false;
        }
        else {
            this.isAvailable = true;
        }
    }
}
