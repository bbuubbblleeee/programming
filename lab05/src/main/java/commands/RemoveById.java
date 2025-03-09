package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

public class RemoveById extends Command{
    public RemoveById(){
        super("remove_by_id", "removes from the collection an element whose id equals the given value.", 1, 0);
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
            return new Response("Error: invalid type of argument. Expected long.");
        }
        for (Dragon dragon : dragons){
            if (dragon.getId() == id){
                collectionManager.remove(dragon);
                return new Response("The element, whose id = " + id + ", was successfully removed from the collection.");
            }
        }
        return new Response("The element with such id wasn't found.");
    }
}
