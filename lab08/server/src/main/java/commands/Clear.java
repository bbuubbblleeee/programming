package commands;

import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;

/**
 * Command clears the collection.
 * <p>
 * Managed by {@link collectionManager.CollectionManager}.
 */

public class Clear extends Command {
    public Clear() {
        super("clear", "очищает коллекцию", 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("CollectionEmpty");
        }
        return getCollectionManager().clear();
    }
}
