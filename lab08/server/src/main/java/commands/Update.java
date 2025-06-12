package commands;

import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;


/**
 * The class implements the update command.
 * The command updates the value of a collection item whose id is equal to the specified one.
 */
public class Update extends Command {
    public Update() {
        super("update", "обновляет значение элемента коллекции, идентификатор которого равен заданному.", 1);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("CollectionEmpty");
        }
        long id;
        try {
            id = Long.parseLong(request.args()[0]);
            return getCollectionManager().update(id, request.dragons().get(0));
        } catch (NumberFormatException e) {
            return new Response("ArgumentType|long");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }
    }
}
