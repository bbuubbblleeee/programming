package commands;

import transfer.Request;
import transfer.Response;

public class Clear extends Command{
    public Clear(){
        super("clear", "clears the collection", 0, 0);
    }
    @Override
    public Response execute(Request request) {
        if (collectionManager.isCollectionEmpty()){
            return new Response("The collection is empty, there's no point in running this command.");
        }
        collectionManager.clear();
        return new Response("Collection was cleared.");
    }
}
