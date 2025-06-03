package commands;

import transfer.Request;
import transfer.Response;


/**
 * An interface that specifies the structure of the commands.
 * Each command contains a name, a target, the number of dragons needed, and the number of arguments needed.
 */
public abstract class Command {
    private final String commandName;
    private final String purpose;
    private final int requiredArgs;

    public Command(String commandName, String purpose, int requiredArgs) {
        this.commandName = commandName;
        this.purpose = purpose;
        this.requiredArgs = requiredArgs;
    }


    abstract public Response execute(Request request);

    public String getName() {
        return commandName;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getRequiredArgs() {
        return requiredArgs;
    }

}
