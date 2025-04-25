package commands;

import transfer.Request;
import transfer.Response;

import static main.ServerMain.getCollectionManager;

/**
 * Command clears the collection.
 * <p>
 * Managed by {@link collectionManager.CollectionManager}.
 */

public class Clear extends Command {
    public Clear() {
        super("clear", "очищает коллекцию", 0, 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        getCollectionManager().clear();
        return new Response("Коллекция очищена.");
    }
}
