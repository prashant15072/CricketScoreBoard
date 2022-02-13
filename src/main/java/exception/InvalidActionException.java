package exception;

public class InvalidActionException extends RuntimeException {
    public final String cause;


    public InvalidActionException(String cause) {
        this.cause = cause;
    }
}
