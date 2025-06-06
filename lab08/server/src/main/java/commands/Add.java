package commands;

import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;

/**
 * Command adds an element {@link collection.Dragon} to the collection.
 * * <p>
 * * Managed by {@link collectionManager.CollectionManager}.
 */

public class Add extends Command {
    public Add() {
        super("add", "добавляет новый элемент в коллекцию.", 0);
    }

    @Override
    public Response execute(Request request) {
        return getCollectionManager().add(request.dragons().get(0));
    }
}
