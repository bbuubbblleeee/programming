package commands;

import transfer.Request;
import transfer.Response;

/**
 * Класс реализует команду info.
 * Команда выводит информацию о коллекции (тип, дата инициализации и количество элементов).
 */
public class Info extends Command{
    public Info(){
        super("info", "shows information about the collection.", 0, 0);
    }
    @Override
    public Response execute(Request request) {
        if (collectionManager.isCollectionEmpty()){
            return new Response("The collection is empty, there's no point in running this command.");
        }
        return new Response(collectionManager.info().toString());
    }
}
