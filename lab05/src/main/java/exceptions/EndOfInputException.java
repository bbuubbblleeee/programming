package exceptions;

/**
 * Исключение, которое сигнализирует о завершении ввода пользователем.
 */
public class EndOfInputException extends RuntimeException {
    public EndOfInputException(String message) {
        super("End of " + message);
    }
}
