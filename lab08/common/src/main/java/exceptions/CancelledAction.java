package exceptions;

public class CancelledAction extends RuntimeException {
    public CancelledAction(String message) {
        super(message);
    }
}
