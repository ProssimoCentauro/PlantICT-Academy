public abstract class Player implements PlayerManager {
    final String RESET = "\u001B[0m";

    final String RED = "\u001B[1;31m";
    final String GREEN = "\u001B[1;32m";
    final String YELLOW = "\u001B[1;33m";
    final String BLUE = "\u001B[1;34m";

    private String nickname;
    private int id;
    private int level;

    Player(String nickname, int id, int level) {
        this.nickname = nickname;
        this.id = id;
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public abstract void description(String description);
    public abstract void changeAvailable();
}
