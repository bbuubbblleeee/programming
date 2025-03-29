package commands;

import collection.Dragon;
import exceptions.WrongArgumentException;
import transfer.Request;
import transfer.Response;

import static collectionManager.CollectionManager.dragons;

/**
 * The class implements the remove_by_id command.
 * The command removes an item from the collection by its id.
 */
public class RemoveById extends Command{
    public RemoveById(){
        super("remove_by_id", "удаляет элемент из коллекции по его идентификатору.", 1, 0);
    }
    @Override
    public Response execute(Request request) {
        if (request.collectionManager().isCollectionEmpty()){
            return new Response("Коллекция пуста, выполнение этой команды не имеет смысла.");
        }
        long id;
        try{
            id = Long.parseLong(request.args()[0]);
            if (id < 0){
                throw new WrongArgumentException("Недопустимое значение.\nОжидалось значение > 0.");
            }
        }
        catch (NumberFormatException e){
            return new Response("Недопустимое значение.\nОжидался аргумент типа long.");
        }
        catch (WrongArgumentException ex){
            return new Response(ex.getMessage());
        }
        for (Dragon dragon : dragons){
            if (dragon.getId() == id){
                request.collectionManager().remove(dragon);
                return new Response("Элемент, чей идентификатор = " + id + ", был успешно удалён из коллекции.");
            }
        }
        return new Response("Элемент с идентификатором = " + id + " не был найден.");
    }
}
