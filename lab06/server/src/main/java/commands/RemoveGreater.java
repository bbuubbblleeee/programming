package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static collectionManager.CollectionManager.dragons;
import static main.ServerMain.getCollectionManager;


/**
 * The class implements the remove_greater command.
 * The command removes from the collection all elements exceeding the specified one.
 */
public class RemoveGreater extends Command {
    public RemoveGreater() {
        super("remove_greater", "удаляет из коллекции все элементы, превышающие заданный.", 0, 1);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        long id = request.dragons().get(0).getId();
        List<Dragon> dragonList = dragons.stream().filter(dragon -> dragon.getId() > id).toList();

        if (dragonList.isEmpty()){
            return new Response("Элементы, превышающие заданный, не найдены.");
        }
        Iterator<Dragon> iterator = dragons.iterator();
        while (iterator.hasNext()){
            Dragon dragon = iterator.next();
            if (dragonList.contains(dragon)){
                iterator.remove();
            }
        }
        return new Response("Все элементы, превышающие заданный, были удалены из коллекции.");
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
