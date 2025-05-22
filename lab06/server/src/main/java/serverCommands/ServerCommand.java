package serverCommands;

import transfer.Request;
import transfer.Response;

public abstract class ServerCommand {
    abstract public Response execute();
}
