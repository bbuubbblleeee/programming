package exceptions;

/**
 * Исключение, указывающее на недопустимый файл или ошибки при его обработке.
 */
public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String message) {
        super(message);
    }
}
