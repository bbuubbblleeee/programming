package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import java.util.StringJoiner;

import static collectionManager.CollectionManager.dragons;

/**
 * The class implements the show command.
 * The command outputs all elements of the collection in a string representation.
 */
public class Show extends Command{
    public Show(){
        super("show", "выводит в стандартный поток вывода все элементы коллекции в строковом представлении.", 0, 0);
    }

    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Dragon dragon : dragons){
            stringJoiner.add(dragon.toString());
        }
        return new Response(stringJoiner.toString());
    }
}
