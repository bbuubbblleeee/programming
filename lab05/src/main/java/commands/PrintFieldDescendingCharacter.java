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
 * Класс реализует команду print_field_descending_character.
 * Команда выводит значения поля character всех элементов в порядке убывания.
 */

public class PrintFieldDescendingCharacter extends Command{
    public PrintFieldDescendingCharacter(){
        super("print_field_descending_character", "prints the character field values of all elements in descending order.", 0, 0);
    }
    @Override
    public Response execute(Request request) {
        if (collectionManager.isCollectionEmpty()){
            return new Response("The collection is empty, there's no point in running this command.");
        }
        StringJoiner stringJoiner = new StringJoiner("\n");
        List<Dragon> dragonsReversed = new ArrayList<Dragon>(dragons);
        for (int i = dragonsReversed.size() - 1; i >= 0; i--){
            DragonCharacter character = dragonsReversed.get(i).getCharacter();
            if (character == null){
                continue;
            }
            stringJoiner.add(character.toString());
        }
        String res = stringJoiner.toString();
        return new Response(res.isEmpty() ? "The character field is empty for all elements in the collection." : res);
    }
}
