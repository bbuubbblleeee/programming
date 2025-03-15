package commands;

import transfer.Request;
import transfer.Response;


/**
 * The class implements the update command.
 * The command updates the value of a collection item whose id is equal to the specified one.
 */
public class Update extends Command{
    public Update(){
        super("update", "update the value of the collection item whose id is equal to the given one.", 1, 1);
    }

    @Override
    public Response execute(Request request) {
        if (collectionManager.isCollectionEmpty()){
            return new Response("The collection is empty, there's no point in running this command.");
        }
        long id;
        try{
            id = Long.parseLong(request.args()[0]);
        }
        catch (NumberFormatException e){
            return new Response("Invalid type of argument. Expected long.");
        }
        return collectionManager.update(id, request.dragons().get(0));
    }
}
