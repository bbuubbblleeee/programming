package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

/**
 * The class implements the remove_any_by_age command.
 * The command removes from the collection one element whose age field value is equivalent to the specified one.
 */

public class RemoveAnyByAge extends Command{
    public RemoveAnyByAge(){
        super("remove_any_dy_age", "удаляет из коллекции один элемент, значение поля age которого эквивалентно заданному.", 1, 0);
    }
    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        int age;
        try{
            age = Integer.parseInt(request.args()[0]);
        }
        catch (NumberFormatException e){
            return new Response("Недопустимое значение.\nОжидался аргумент типа int.");
        }
        for (Dragon dragon : dragons){
            if (dragon.getAge() == age){
                request.collectionManager().remove(dragon);
                return new Response("Элемент, чей возраст = " + age + ", был успешно удалён из коллекции.");
            }
        }
        return new Response("Элемент с таким возрастом не был найден.");
    }
}
