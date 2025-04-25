package commands;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;
import static main.ServerMain.getCollectionManager;

/**
 * The class implements the count_by_age command.
 * The command counts the number of items in the {@link collection.Dragon} collection that have the same age as the specified age.
 */
public class CountByAge extends Command {
    public CountByAge() {
        super("count_by_age", "выводит количество элементов, значение поля age которых равно заданному", 1, 0);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        int age;
        try {
            age = Integer.parseInt(request.args()[0]);
        } catch (Exception e) {
            return new Response("Недопустимое значение.\nОжидался аргумент типа int.");
        }
        long count = 0L;
        for (Dragon dragon : dragons) {
            if (dragon.getAge() == age) {
                count++;
            }
        }
        return new Response("Количество элементов, значение поля age которых равно " + age + ": " + count);
    }
}
