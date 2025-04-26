package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import java.util.StringJoiner;
import java.util.stream.Collectors;

import static collectionManager.CollectionManager.dragons;
import static main.ServerMain.getCollectionManager;

/**
 * The class implements the show command.
 * The command outputs all elements of the collection in a string representation.
 */
public class Show extends Command {
    public Show() {
        super("show", "выводит в стандартный поток вывода все элементы коллекции в строковом представлении.", 0, 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        return new Response(dragons.stream().map(Dragon::toString).collect(Collectors.joining("\n")));
    }
}
