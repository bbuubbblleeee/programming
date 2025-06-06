package exceptions;

public class DbErrorException extends RuntimeException {
    public DbErrorException(String message) {
        super(message);
    }
}
