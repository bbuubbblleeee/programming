package collectionManager;

import collection.Dragon;
import dao.DateAndDragons;
import dao.DbDao;
import exceptions.DbErrorException;
import transfer.Response;
import users.User;

import java.util.Iterator;
import java.util.function.Predicate;

public class DatabaseManager extends CollectionManager {
    private DbDao dao = new DbDao();
    public DatabaseManager() {
        try {
            DateAndDragons dateAndDragons = dao.get();
            date = dateAndDragons.date();
            dragons = dateAndDragons.dragons();
            System.out.println("Коллекция успешно считана из базы данных.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Response add(Dragon dragon) {
        try {
            long id = dao.add(dragon, User.getLogin());
            dragon.setId(id);
            dragon.setOwner(User.getLogin());
            dragons.add(dragon);
            return new Response("Дракон был успешно добавлен.");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }
    }

    @Override
    public Response addIfMax(Dragon dragon, long lastId) {
        try {
            long id = dao.add_if_max(dragon, lastId, User.getLogin());
            dragon.setId(id);
            dragon.setOwner(User.getLogin());
            dragons.add(dragon);
            return new Response("Дракон был успешно добавлен.");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }

    }

    @Override
    public void remove(Predicate<Dragon> condition, boolean one, String errorMessage, String... conditionString) {
        try {
            if (dao.remove(conditionString[0], User.getLogin()) == 0) {
                throw new DbErrorException("Отсутствуют строки, подходящие под условие.");
            }
            removeFromCollection(condition, one, errorMessage);
        }
        catch (Exception e){
            throw new DbErrorException(e.getMessage());
        }
    }


    @Override
    public Response update(Long id, Dragon dragonNew) {
        dao.update(id, dragonNew, User.getLogin());
        return updateInCollection(id, dragonNew);
    }

    @Override
    public Response clear() {
        try {
            dao.clear(User.getLogin());
            dragons.removeIf(dragon -> dragon.getOwner().equals(User.getLogin()));
            return new Response("Коллекция очищена.");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }

    }

    public void signUp(String login, String password){
        try{
            dao.signUpUser(login, password);
        } catch (Exception e) {
            throw new DbErrorException(e.getMessage());
        }
    }

    public void checkUser(String login, String password){
        try{
            dao.checkUser(login, password);
        } catch (Exception e) {
            throw new DbErrorException(e.getMessage());
        }
    }


}
