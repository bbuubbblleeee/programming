package commands;

import transfer.Request;
import transfer.Response;

import static main.ServerMain.getCollectionManager;

/**
 * Command adds an element {@link collection.Dragon} to the collection.
 * * <p>
 * * Managed by {@link collectionManager.CollectionManager}.
 */

public class Add extends Command {
    public Add() {
        super("add", "добавляет новый элемент в коллекцию.", 0, 1);
    }

    @Override
    public Response execute(Request request) {
        getCollectionManager().add(request.dragons().get(0));
        return new Response("Дракон был успешно добавлен.");
    }
}
