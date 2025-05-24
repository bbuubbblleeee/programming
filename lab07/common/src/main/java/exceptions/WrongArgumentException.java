package exceptions;

/**
 * Exception indicating an invalid argument.
 */
public class WrongArgumentException extends RuntimeException {
    public WrongArgumentException(String message) {
        super(message);
    }
}
