package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;
import static server.ServerMain.getCollectionManager;


/**
 * The class implements the remove_greater command.
 * The command removes from the collection all elements exceeding the specified one.
 */
public class RemoveGreater extends Command {
    public RemoveGreater() {
        super("remove_greater", "удаляет из коллекции все элементы, превышающие заданный.", 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        Dragon dragonInput = request.dragons().get(0);

        try {
            getCollectionManager().add(dragonInput);
            long id = dragonInput.getId();
            dragons.remove(dragonInput);
            getCollectionManager().remove(dragon -> dragon.getId() > id, false, "Элементы, превышающие заданный, не найдены.", "id > " + id);
            return new Response("Все элементы, превышающие заданный, были удалены из коллекции.");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }

//        for (Dragon dragon : dragons) {
//            if (dragon.getId() > id) {
//                getCollectionManager().remove(dragon);
//                count++;
//            }
//        }
//        if (count > 0) {
//            return new Response("Все элементы, превышающие заданный, были удалены из коллекции.");
//        }
//        return new Response("Элементы, превышающие заданный, не найдены.");
    }
}
