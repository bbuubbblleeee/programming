package commands;

import collection.Dragon;
import collection.DragonCharacter;
import transfer.Request;
import transfer.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static collectionManager.CollectionManager.dragons;

/**
 * The class implements the print_field_descending_character command.
 * The command prints the character field values of all elements in descending order.
 */

public class PrintFieldDescendingCharacter extends Command{
    public PrintFieldDescendingCharacter(){
        super("print_field_descending_character", "выводит значения поля character всех элементов в порядке убывания.", 0, 0);
    }
    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        StringJoiner stringJoiner = new StringJoiner("\n");
        List<Dragon> dragonsReversed = new ArrayList<>(dragons);
        for (int i = dragonsReversed.size() - 1; i >= 0; i--){
            DragonCharacter character = dragonsReversed.get(i).getCharacter();
            if (character == null){
                continue;
            }
            stringJoiner.add(character.toString());
        }
        String res = stringJoiner.toString();
        return new Response(res.isEmpty() ? "Поле character пусто у всех элементов коллекции." : res);
    }
}
