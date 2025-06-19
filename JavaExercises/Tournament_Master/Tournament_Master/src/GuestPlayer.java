import exceptions.PlayerNotAvailableException;

public class GuestPlayer extends Player {
    double RemainingTime;
    boolean isAvailable = true;
    PlayerType playerType = PlayerType.GUEST;

    GuestPlayer(String nickname, int id, int level,  double time) {
        super(nickname, id, level);
        this.RemainingTime = time;
    }

    PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public void description(String description) {
        System.out.println("Player name:" + super.getNickname());
        System.out.println("Player id:" + super.getId());
        System.out.println("Player level:" + super.getLevel());
        System.out.println("Player type: Casual Player");
        System.out.println(super.getNickname() + " Remaining Time: " + RemainingTime + " hours");
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
