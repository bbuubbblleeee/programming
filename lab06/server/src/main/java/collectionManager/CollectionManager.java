package collectionManager;


import client.ReadData;
import collection.Dragon;
import dao.FileDao;
import transfer.Response;

import java.nio.file.Path;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * The class stores a collection and contains methods that implement commands that work directly with the collection.
 * Methods that implement commands:
 * <ul>
 *  <li> {@link #add(Dragon)} - implements the add command (adds an item to the collection).</li>
 *  <li> {@link #remove(Dragon)} - implements “remove%” commands (removes an item from the collection).</li>
 *  <li> {@link #info()} - implements the info command (provides basic data about the collection).</li>
 *  <li> {@link #update(Long, ReadData)} - implements the update command (updates item data from the collection).</li>
 *  <li> {@link #clear()} - implements the clear command (clears the collection).</li>
 *  <li> {@link #save()} - implements the save command (saves the collection to a file).</li>
 * </ul>
 */
public class CollectionManager {
    public static TreeSet<Dragon> dragons = new TreeSet<>();
    protected static String date;
    protected final FileDao fileDao = FileDao.getInstance(); //TODO мб сделать статик?
    protected final Path idFilePath = Path.of("src\\main\\id.json");

    public void add(Dragon dragon) {
        dragons.add(dragon);
    }

    public void remove(Dragon dragon) {
        dragons.remove(dragon);
    }

    public StringJoiner info() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Информация о коллекции.");
        stringJoiner.add("Тип: Dragon");
        stringJoiner.add("Дата инициализации: " + date);
        stringJoiner.add("Количество элементов: " + dragons.size());
        return stringJoiner;
    }

    public Response update(Long id, Dragon dragonNew) {
        Dragon dragonUpdate = null;
        boolean found = false;
        for (Dragon dragon : dragons) {
            if (dragon.getId().equals(id)) {
                dragonUpdate = dragon;
                found = true;
            }
        }
        if (!found) {
            return new Response("Элемент, идентификатор которого равен " + id + ", не был найден.");
        }
        dragonUpdate.setName(dragonNew.getName());
        dragonUpdate.setAge(dragonNew.getAge());
        dragonUpdate.setCharacter(dragonNew.getCharacter());
        dragonUpdate.setColor(dragonNew.getColor());
        dragonUpdate.setCoordinateX(dragonNew.getCoordinates().getX());
        dragonUpdate.setCoordinateY(dragonNew.getCoordinates().getY());
        dragonUpdate.setDepthCave(dragonNew.getCave().getDepth());
        dragonUpdate.setNumberOfTreasures(dragonNew.getCave().getNumberOfTreasures());
        dragonUpdate.setColor(dragonNew.getColor());
        return new Response("Элемент был успешно обновлен.");
    }

    public void clear() {
        dragons.clear();
        Dragon.setFreeId();
    }

    public void save() {
        try {
            fileDao.save(dragons);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isCollectionEmpty() {
        return dragons.isEmpty();
    }

//    public void saveLastId(Long id) throws FileNotFoundException, FilePermissionException {
//        fileDao.saveLastId(id, idFilePath);
//    }
//
//    public Long getLastId() throws FileNotFoundException, FilePermissionException {
//        return fileDao.getLastId(idFilePath);
//    }
}
