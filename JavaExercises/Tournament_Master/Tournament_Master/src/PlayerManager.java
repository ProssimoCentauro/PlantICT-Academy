public interface PlayerManager {
    void join(Match match);
    void leave(Match match);
    boolean isAvailable();
}
