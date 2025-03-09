package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import java.util.StringJoiner;

import static collectionManager.CollectionManager.dragons;

public class Show extends Command{
    public Show(){
        super("show", "prints all elements of the collection in string representation.", 0, 0);
    }

    @Override
    public Response execute(Request request) {
        if (collectionManager.isCollectionEmpty()){
            return new Response("The collection is empty, there's no point in running this command.");
        }
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Dragon dragon : dragons){
            stringJoiner.add(dragon.toString());
        }
        return new Response(stringJoiner.toString());
    }
}
