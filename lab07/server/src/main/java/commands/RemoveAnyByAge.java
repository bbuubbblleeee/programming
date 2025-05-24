package commands;

import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;

/**
 * The class implements the remove_any_by_age command.
 * The command removes from the collection one element whose age field value is equivalent to the specified one.
 */

public class RemoveAnyByAge extends Command {
    public RemoveAnyByAge() {
        super("remove_any_dy_age", "удаляет из коллекции один элемент, значение поля age которого эквивалентно заданному.", 1, 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        int age;
        try {
            age = Integer.parseInt(request.args()[0]);
            getCollectionManager().remove(dragon -> dragon.getAge() == age, true, "Элемент с возрастом = " + age + " не был найден.", "age = " + age);
            return new Response("Элемент, чей возраст = " + age + ", был успешно удалён из коллекции.");
        } catch (NumberFormatException numberFormatException) {
            return new Response("Недопустимое значение.\nОжидался аргумент типа int.");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }
    }
}
