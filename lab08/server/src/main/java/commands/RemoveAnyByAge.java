package commands;

import transfer.Request;
import transfer.Response;
import users.User;

import static server.ServerMain.getCollectionManager;

/**
 * The class implements the remove_any_by_age command.
 * The command removes from the collection one element whose age field value is equivalent to the specified one.
 */

public class RemoveAnyByAge extends Command {
    public RemoveAnyByAge() {
        super("remove_any_dy_age", "удаляет из коллекции один элемент, значение поля age которого эквивалентно заданному.", 1);
    }

    @Override
    public Response execute(Request request) {
        if (getCollectionManager().isCollectionEmpty()) {
            return new Response("CollectionEmpty");
        }
        int age;
        try {
            age = Integer.parseInt(request.args()[0]);
            getCollectionManager().remove(dragon -> dragon.getAge() == age && dragon.getOwner().equals(User.getLogin()), true, "AgeNotFound", "age = " + age);
            return new Response("RemoveSuccess");
        } catch (NumberFormatException numberFormatException) {
            return new Response("ArgumentType|int");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }
    }
}
