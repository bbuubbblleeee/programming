package commands;

import collection.Dragon;
import exceptions.WrongArgumentException;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

/**
 * Класс реализует команду remove_by_id.
 * Команда удаляет элемент из коллекции по его id.
 */
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
            if (id < 0){
                throw new WrongArgumentException("Invalid value.\nExpected value > 0.");
            }
        }
        catch (NumberFormatException e){
            return new Response("Invalid type of argument. Expected long.");
        }
        catch (WrongArgumentException ex){
            return new Response(ex.getMessage());
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
