package exceptions;

/**
 * Exception indicating that the user does not have the necessary permissions for using the file.
 */
public class FilePermissionException extends RuntimeException {
    public FilePermissionException(String message) {
        super(message);
    }
}
