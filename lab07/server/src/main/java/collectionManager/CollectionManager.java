package collectionManager;


import collection.Dragon;
import dao.DAO;
import exceptions.WrongArgumentException;
import transfer.Response;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 * The class stores a collection and contains methods that implement commands that work directly with the collection.
 * Methods that implement commands:
 * <ul>
 *  <li> {@link #add(Dragon)} - implements the add command (adds an item to the collection).</li>
 *  <li> {@link #info()} - implements the info command (provides basic data about the collection).</li>
 *  <li> {@link #update(Long, Dragon)} - implements the update command (updates item data from the collection).</li>
 *  <li> {@link #clear()} - implements the clear command (clears the collection).</li>
 *  <li> {@link #save()} - implements the save command (saves the collection to a file).</li>
 * </ul>
 */
public abstract class CollectionManager {
    public static TreeSet<Dragon> dragons = new TreeSet<>();
    protected static String date;

    public CollectionManager(DAO dao){
    };

    protected CollectionManager(){};

    abstract public Response add(Dragon dragon);

    abstract public Response addIfMax(Dragon dragon, long id);


    abstract public Response clear();

    abstract public Response update(Long id, Dragon dragonNew);

    abstract public void remove(Predicate<Dragon> condition, boolean one, String errorMessage, String... conditionString);

    public Response info() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Информация о коллекции.");
        stringJoiner.add("Тип: Dragon");
        stringJoiner.add("Дата инициализации: " + date);
        stringJoiner.add("Количество элементов: " + dragons.size());
        return new Response(stringJoiner.toString());

    }

    public Response count_by_age(int age) {
        long count = dragons.stream().filter(dragon -> dragon.getAge() == age).toList().size();
        return new Response("Количество элементов, значение поля age которых равно " + age + ": " + count);
    }


    public void removeFromCollection(Predicate<Dragon> condition, boolean one, String errorMessage) throws WrongArgumentException{
        if (one){
            Dragon dragon = dragons.stream()
                    .filter(condition)
                    .findAny()
                    .orElseThrow(() -> new WrongArgumentException(errorMessage));
            dragons.remove(dragon);
            return;
        }
        List<Dragon> dragonList = dragons.stream().filter(condition).toList();
        if (dragonList.isEmpty()){
            throw new WrongArgumentException(errorMessage);
        }
        for (Dragon dragon : dragonList){
            dragons.remove(dragon);
        }
    }



    protected Response updateInCollection(Long id, Dragon dragonNew){
        return dragons.stream()
                .filter(d -> Objects.equals(d.getId(), id))
                .findFirst()
                .map(dragonUpdate -> {
                    dragonUpdate.setName(dragonNew.getName());
                    dragonUpdate.setAge(dragonNew.getAge());
                    dragonUpdate.setCharacter(dragonNew.getCharacter());
                    dragonUpdate.setColor(dragonNew.getColor());
                    dragonUpdate.setCoordinateX(dragonNew.getCoordinates().getX());
                    dragonUpdate.setCoordinateY(dragonNew.getCoordinates().getY());
                    dragonUpdate.setDepthCave(dragonNew.getCave().getDepth());
                    dragonUpdate.setType(dragonNew.getType());
                    dragonUpdate.setNumberOfTreasures(dragonNew.getCave().getNumberOfTreasures());
                    return new Response("Элемент успешно обновлен.");
                })
                .orElse(new Response("Элемент с ID " + id + " не найден."));
    }


//    public void save() {
//        try {
//            dao.save(dragons);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public boolean isCollectionEmpty() {
        return dragons.isEmpty();
    }

}
