package commands;

import transfer.Request;
import transfer.Response;

/**
 * The class implements the save command.
 * The command saves the collection to a file.
 */
public class Save extends Command{
    public Save(){
        super("save", "saves the collection to a file.", 0, 0);
    }
    @Override
    public Response execute(Request request) {
        if (collectionManager.isCollectionEmpty()){
            return new Response("The collection is empty, there's no point in running this command.");
        }
        try{
            collectionManager.save();
            return new Response("The collection was saved.");
        }
        catch (Exception e){
            return new Response(e.getMessage() + ".\nThe collection wasn't saved.");
        }
    }
}
