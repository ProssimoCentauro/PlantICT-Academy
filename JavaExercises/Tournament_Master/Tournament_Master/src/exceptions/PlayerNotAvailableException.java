package exceptions;

public class PlayerNotAvailableException extends RuntimeException {
    public PlayerNotAvailableException(String message) {
        super(message);
    }
}
