package commands;

import transfer.Request;
import transfer.Response;

/**
 * The class implements the save command.
 * The command saves the collection to a file.
 */
public class Save extends Command{
    public Save(){
        super("save", "сохраняет коллекцию в файл.", 0, 0);
    }
    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        try{
            request.collectionManager().save();
            return new Response("Коллекция была успешно сохранена.");
        }
        catch (Exception e){
            return new Response(e.getMessage() + ".\nКоллекция не была сохранена.");
        }
    }
}
