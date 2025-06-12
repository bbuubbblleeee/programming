package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import java.util.stream.Collectors;

import static collectionManager.CollectionManager.dragons;
import static server.ServerMain.getCollectionManager;

/**
 * The class implements the show command.
 * The command outputs all elements of the collection in a string representation.
 */
public class Show extends Command {
    public Show() {
        super("show", "показывает элементы коллекции.", 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("CollectionEmpty");
        }
        return new Response(dragons.stream().map(Dragon::toString).collect(Collectors.joining("\n\n")));
    }
}
