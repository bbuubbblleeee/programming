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
        super("add_if_max", "adds new item to the collection if its value exceeds the value of the largest element of this collection", 0, 1);
    }
    @Override
    public Response execute(Request request) {
        Dragon dragon = request.dragons().get(0);
        if (dragon.getId() > getLastId()){
            collectionManager.add(dragon);
            return new Response("Dragon successfully added.");
        }
        return new Response("The dragon wasn't added to the collection, as it's smaller than the biggest one.");

    }

    public Long getLastId(){
        if (dragons.isEmpty()){
            return -1L;
        }
        return dragons.last().getId();
    }
}
