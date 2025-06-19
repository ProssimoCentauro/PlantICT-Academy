import exceptions.PlayerNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public class ProPlayer extends Player implements PlayerManager, MatchesWon {
    String team;
    String sponsor;
    boolean isAvailable = true;
    int matchesWon = 0;
    List<Match> matchesWonList = new ArrayList<>();
    PlayerType playerType = PlayerType.PRO;

    ProPlayer(String nickname, int id, int level, String team, String sponsor) {
        super(nickname, id, level);
        this.team = team;
        this.sponsor = sponsor;
    }

    PlayerType getPlayerType() {
        return playerType;
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
        System.out.println("Player type: Pro Player");
        System.out.println(super.getNickname() + " team:" + team);
        System.out.println(super.getNickname() + " sponsor:" + sponsor);
        System.out.println("About: " + getNickname() + " " + description);
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
