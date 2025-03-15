package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

/**
 * The class implements the remove_any_by_age command.
 * The command removes from the collection one element whose age field value is equivalent to the specified one.
 */

public class RemoveAnyByAge extends Command{
    public RemoveAnyByAge(){
        super("remove_any_dy_age", "removes from the collection one element whose age equals the given value.", 1, 0);
    }
    @Override
    public Response execute(Request request) {
        if (collectionManager.isCollectionEmpty()){
            return new Response("The collection is empty, there's no point in running this command.");
        }
        int age;
        try{
            age = Integer.parseInt(request.args()[0]);
        }
        catch (NumberFormatException e){
            return new Response("Invalid type of argument. Expected int.");
        }
        for (Dragon dragon : dragons){
            if (dragon.getAge() == age){
                collectionManager.remove(dragon);
                return new Response("The element, whose age = " + age + ", was successfully removed from the collection.");
            }
        }
        return new Response("The element with such age wasn't found.");
    }
}
