package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;
import static server.ServerMain.getCollectionManager;

/**
 * Command adds an element {@link collection.Dragon} to the collection if it's bigger than the max one.
 * <p>
 * Managed by {@link collectionManager.CollectionManager}.
 */

public class AddIfMax extends Command {
    public AddIfMax() {
        super("add_if_max", "добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции", 0, 1);
    }

    @Override
    public Response execute(Request request) {
        Dragon dragon = request.dragons().get(0);
        return getCollectionManager().add_if_max(request.dragons().get(0), getLastId());
    }

    public Long getLastId() {
        if (dragons.isEmpty()) {
            return -1L;
        }
        return dragons.last().getId();
    }
}
