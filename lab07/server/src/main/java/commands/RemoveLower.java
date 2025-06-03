package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;
import static server.ServerMain.getCollectionManager;

/**
 * The class implements the remove_lower command.
 * The command removes all elements smaller than the specified one from the collection.
 */
public class RemoveLower extends Command {
    public RemoveLower() {
        super("remove_lower", "удаляет из коллекции все элементы, меньшие, чем заданный.", 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        Dragon dragonInput = request.dragons().get(0);
        try{
            getCollectionManager().add(dragonInput);
            long id = dragonInput.getId();
            dragons.remove(dragonInput);
            getCollectionManager().remove(dragon -> dragon.getId() < id, false, "Элементы, меньшие заданного, не найдены.", "id < " + id);
            return new Response("Все элементы, меньшие заданного, были удалены из коллекции.");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }
    }
}
