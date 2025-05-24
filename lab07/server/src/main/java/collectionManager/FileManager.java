package collectionManager;

import collection.Dragon;
import dao.DateAndDragons;
import dao.FileDao;
import transfer.Response;

import java.util.function.Predicate;

/**
 * The class binds and implements loading data from a file and saving it to a collection.
 */

public class FileManager extends CollectionManager {
    private FileDao dao = FileDao.getInstance();
    public FileManager() {
        try {
            DateAndDragons dateAndDragons = dao.get();
            date = dateAndDragons.date();
            dragons = dateAndDragons.dragons();
            System.out.println("Коллекция успешно считана из файла.");
        } catch (Exception e) {
            System.out.println("Недопустимый файл.");
        }
    }

    @Override
    public Response add(Dragon dragon) {
        dragons.add(dragon);
        return new Response("Дракон был успешно добавлен.");
    }

    @Override
    public Response add_if_max(Dragon dragon, long id) {
        if (dragon.getId() > id){
            return add(dragon);
        };
        return new Response("Дракон не был добавлен в коллекцию, так как его значение меньше значения наибольшего элемента коллекции.");
    }

    @Override
    public void remove(Predicate<Dragon> condition, boolean one, String errorMessage, String... conditionString) {
        removeFromCollection(condition, one, errorMessage);
    }

    @Override
    public Response update(Long id, Dragon dragonNew) {
        return updateInCollection(id, dragonNew);
    }

    @Override
    public Response clear() {
        try {
            dragons.clear();
            Dragon.setFreeId();
            return new Response("Коллекция очищена.");
        }
        catch (Exception e){
            return new Response("Ошибка очистки коллекции.");
        }
    }

}
