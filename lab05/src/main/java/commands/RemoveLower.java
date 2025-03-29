package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

/**
 * The class implements the remove_lower command.
 * The command removes all elements smaller than the specified one from the collection.
 */
public class RemoveLower extends Command{
    public RemoveLower(){
        super("remove_lower", "удаляет из коллекции все элементы, меньшие, чем заданный.", 0, 1);
    }
    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        long id = request.dragons().get(0).getId();
        long count = 0L;
        for (Dragon dragon : dragons){
            if (dragon.getId() < id){
                request.collectionManager().remove(dragon);
                count++;
            }
        }
        if (count > 0){
            return new Response("Все элементы, меньшие заданного, были удалены из коллекции.");
        }
        return new Response("Элементы, меньшие заданного, не найдены.");
    }
}
