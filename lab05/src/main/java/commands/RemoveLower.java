package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

/**
 * Класс реализует команду remove_lower.
 * Команда удаляет из коллекции все элементы, меньшие, чем заданный.
 */
public class RemoveLower extends Command{
    public RemoveLower(){
        super("remove_lower", "removes from the collection all elements smaller than the specified value.", 0, 1);
    }
    @Override
    public Response execute(Request request) {
        if (collectionManager.isCollectionEmpty()){
            return new Response("The collection is empty, there's no point in running this command.");
        }
        long id = request.dragons().get(0).getId();
        long count = 0L;
        for (Dragon dragon : dragons){
            if (dragon.getId() < id){
                collectionManager.remove(dragon);
                count++;
            }
        }
        return new Response(count + " elements was removed from the collection.");
    }
}
