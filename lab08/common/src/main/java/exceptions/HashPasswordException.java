package exceptions;

public class HashPasswordException extends RuntimeException {
    public HashPasswordException(String message) {
        super(message);
    }
}
