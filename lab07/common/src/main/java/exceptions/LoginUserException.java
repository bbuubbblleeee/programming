package exceptions;

public class LoginUserException extends RuntimeException {
    public LoginUserException(String message) {
        super(message);
    }
}
