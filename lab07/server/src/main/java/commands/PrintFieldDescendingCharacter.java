package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static collectionManager.CollectionManager.dragons;
import static server.ServerMain.getCollectionManager;

/**
 * The class implements the print_field_descending_character command.
 * The command prints the character field values of all elements in descending order.
 */

public class PrintFieldDescendingCharacter extends Command {
    public PrintFieldDescendingCharacter() {
        super("print_field_descending_character", "выводит значения поля character всех элементов в порядке убывания.", 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        List<Dragon> dragonsReversed = new ArrayList<>(dragons);
        String res = dragonsReversed.stream().sorted(Comparator.reverseOrder()).
                map(dragon -> dragon.getCharacter() == null ? "-" : dragon.getCharacter().toString()).collect(Collectors.joining("\n"));
        return new Response(res.isEmpty() ? "Поле character пусто у всех элементов коллекции." : res);
    }
}
