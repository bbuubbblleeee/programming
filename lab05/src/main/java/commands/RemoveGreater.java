package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;


/**
 * The class implements the remove_greater command.
 * The command removes from the collection all elements exceeding the specified one.
 */
public class RemoveGreater extends Command {
    public RemoveGreater(){
        super("remove_greater", "удаляет из коллекции все элементы, превышающие заданный.", 0, 1);
    }
    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        long id = request.dragons().get(0).getId();
        long count = 0L;
        for (Dragon dragon : dragons){
            if (dragon.getId() > id){
                request.collectionManager().remove(dragon);
                count++;
            }
        }
        if (count > 0){
            return new Response("Все элементы, превышающие заданный, были удалены из коллекции.");
        }
        return new Response("Элементы, превышающие заданный, не найдены.");
    }
}
