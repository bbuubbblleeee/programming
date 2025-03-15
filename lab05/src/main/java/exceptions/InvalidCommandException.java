package exceptions;

/**
 * Exception indicating entry of a non-existent command.
 */
public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("Invalid command.");
    }
}
