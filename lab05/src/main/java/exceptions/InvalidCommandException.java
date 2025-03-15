package exceptions;

/**
 * Исключение, указывающее на ввод несуществующей команды.
 */
public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("Invalid command.");
    }
}
