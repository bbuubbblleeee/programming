package commands;

import client.ReadData;
import collection.Dragon;
import transfer.Request;
import transfer.Response;
import client.ReadData.*;


/**
 * The class implements the update command.
 * The command updates the value of a collection item whose id is equal to the specified one.
 */
public class Update extends Command{
    public Update(){
        super("update", "обновляет значение элемента коллекции, идентификатор которого равен заданному.", 1, 1);
    }

    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        long id;
        try{
            id = Long.parseLong(request.args()[0]);
        }
        catch (NumberFormatException e){
            return new Response("Недопустимое значение.\nОжидался аргумент типа long.");
        }
        return request.collectionManager().update(id, request.readData()[0]);
    }
}
