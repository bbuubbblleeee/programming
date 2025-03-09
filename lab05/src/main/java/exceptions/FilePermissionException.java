package exceptions;

public class FilePermissionException extends RuntimeException {
    public FilePermissionException(String message) {
        super(message);
    }
}
