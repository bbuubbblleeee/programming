package transfer;

import collection.Dragon;
import commands.Command;

import java.util.ArrayList;

/**
 * The class creates a request to execute a command.
 * <p>
 * The request contains information about the command, its arguments, and a list of {@link Dragon} objects if they are required.
 */
public record Request(Command command, String[] args, ArrayList<Dragon> dragons) {
}
