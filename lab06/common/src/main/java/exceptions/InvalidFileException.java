package exceptions;

/**
 * Exception indicating an invalid file or errors in its processing.
 */
public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String message) {
        super(message);
    }
}
