package transfer;


/**
 * The class provides a response to the command execution.
 * The class stores messages about the result of command execution.
 */
public record Response (String message) {
    @Override
    public String toString(){
        return message;
    }
}
