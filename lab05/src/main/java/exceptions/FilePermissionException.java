package exceptions;

/**
 * Исключение, указывающее на отсутствие необходимых прав для работы с файлом.
 */
public class FilePermissionException extends RuntimeException {
    public FilePermissionException(String message) {
        super(message);
    }
}
