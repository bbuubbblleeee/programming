package exceptions;

/**
 * Исключение, указывающее на некорректный аргумент.
 */
public class WrongArgumentException extends RuntimeException {
    public WrongArgumentException(String message) {
        super(message);
    }
}
