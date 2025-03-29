package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

/**
 * Command adds an element {@link collection.Dragon} to the collection if it's bigger than the max one.
 * <p>
 * Managed by {@link collectionManager.CollectionManager}.
 */

public class AddIfMax extends Command{
    public AddIfMax(){
        super("add_if_max", "добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции", 0, 1);
    }
    @Override
    public Response execute(Request request) {
        Dragon dragon = request.dragons().get(0);
        if (dragon.getId() > getLastId()){
            request.collectionManager().add(dragon);
            return new Response("Дракон был успешно добавлен.");
        }
        return new Response("Дракон не был добавлен в коллекцию, так как его значение меньше значения наибольшего элемента коллекции.");

    }

    public Long getLastId(){
        if (dragons.isEmpty()){
            return -1L;
        }
        return dragons.last().getId();
    }
}
