package commands;

import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;


/**
 * The class implements the info command.
 * The command outputs information about the collection (type, initialization date and number of elements).
 */
public class Info extends Command {
    public Info() {
        super("info", "выводит в стандартный поток вывода информацию о коллекции.", 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("CollectionEmpty");
        }
        return getCollectionManager().info();
    }
}
