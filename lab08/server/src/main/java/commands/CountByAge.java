package commands;

import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;

/**
 * The class implements the count_by_age command.
 * The command counts the number of items in the {@link collection.Dragon} collection that have the same age as the specified age.
 */
public class CountByAge extends Command {
    public CountByAge() {
        super("count_by_age", "выводит количество элементов, значение поля age которых равно заданному", 1);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("CollectionEmpty");
        }
        int age;
        try {
            age = Integer.parseInt(request.args()[0]);
        } catch (Exception e) {
            return new Response("ArgumentType|int");
        }
        return getCollectionManager().count_by_age(age);
    }
}
