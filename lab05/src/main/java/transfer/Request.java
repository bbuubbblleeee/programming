package transfer;

import client.ReadData;
import collection.Dragon;
import collectionManager.CollectionManager;
import commands.Command;
import io.MyReader;

import java.util.ArrayList;

/**
 * The class creates a request to execute a command.
 * <p>
 * The request contains information about the command, its arguments, and a list of {@link Dragon} objects if they are required.
 */
public record Request(Command command, String[] args, ArrayList<Dragon> dragons, CollectionManager collectionManager, ReadData...readData) {
}
