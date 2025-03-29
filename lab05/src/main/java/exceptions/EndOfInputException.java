package exceptions;

/**
 * Exception that signals when user input is complete.
 */
public class EndOfInputException extends RuntimeException {
    public EndOfInputException(String message) {
        super("Конец " + message + ".");
    }
}
