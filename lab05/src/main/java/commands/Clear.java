package commands;

import transfer.Request;
import transfer.Response;

/**
 * Command clears the collection.
 * <p>
 * Managed by {@link collectionManager.CollectionManager}.
 */

public class Clear extends Command{
    public Clear(){
        super("clear", "очищает коллекцию", 0, 0);
    }
    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        request.collectionManager().clear();
        return new Response("Коллекция очищена.");
    }
}
