package transfer;


import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * The class provides a response to the command execution.
 * The class stores messages about the result of command execution.
 */
public record Response(String message) implements Serializable {
    @Override
    public String toString() {
        return message;
    }
}
