package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import java.util.Iterator;
import java.util.List;

import static collectionManager.CollectionManager.dragons;
import static main.ServerMain.getCollectionManager;

/**
 * The class implements the remove_lower command.
 * The command removes all elements smaller than the specified one from the collection.
 */
public class RemoveLower extends Command {
    public RemoveLower() {
        super("remove_lower", "удаляет из коллекции все элементы, меньшие, чем заданный.", 0, 1);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        long id = request.dragons().get(0).getId();
        List<Dragon> dragonList = dragons.stream().filter(dragon -> dragon.getId() < id).toList();

        if (dragonList.isEmpty()){
            return new Response("Элементы, меньшие заданного, не найдены.");
        }
        Iterator<Dragon> iterator = dragons.iterator();
        while (iterator.hasNext()){
            Dragon dragon = iterator.next();
            if (dragonList.contains(dragon)){
                iterator.remove();
            }
        }
        return new Response("Все элементы, меньшие заданного, были удалены из коллекции.");


    }
}
