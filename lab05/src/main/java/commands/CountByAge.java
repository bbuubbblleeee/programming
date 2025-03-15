package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

/**
 * The class implements the count_by_age command.
 * The command counts the number of items in the {@link collection.Dragon} collection that have the same age as the specified age.
 */
public class CountByAge extends Command {
    public CountByAge(){
        super("count_by_age", "prints the number of items whose age is equal to the specified value", 1, 0);
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
        catch (Exception e){
            return new Response("Invalid type of argument. Expected int.");
        }
        long count = 0L;
        for (Dragon dragon : dragons){
            if (dragon.getAge() == age){
                count++;
            }
        }
        return new Response("The number of items whose age is equal " + age + ": " + count);
    }
}
