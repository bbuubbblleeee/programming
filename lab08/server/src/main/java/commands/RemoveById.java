package commands;

import exceptions.WrongArgumentException;
import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;

/**
 * The class implements the remove_by_id command.
 * The command removes an item from the collection by its id.
 */
public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id", "удаляет элемент из коллекции по его идентификатору.", 1);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("CollectionEmpty");
        }
        long id;
        try {
            id = Long.parseLong(request.args()[0]);
            if (id < 0) {
                throw new WrongArgumentException("ArgumentValidation > 0.");
            }
            getCollectionManager().remove(dragon -> dragon.getId() == id, true, "IdNotFound", "id = " + id);
            return new Response("RemoveSuccess");
        } catch (NumberFormatException e) {
            return new Response("ArgumentType|long");
        } catch (Exception e){
            return new Response(e.getMessage());
        }
    }
}
