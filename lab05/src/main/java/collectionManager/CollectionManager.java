package collectionManager;


import collection.*;

import dao.FileDao;
import transfer.Response;


import java.util.*;

/**
 * The class stores a collection and contains methods that implement commands that work directly with the collection.
 * Methods that implement commands:
 * <ul>
 *  <li> {@link #add(Dragon)} - implements the add command (adds an item to the collection).</li>
 *  <li> {@link #remove(Dragon)} - implements “remove%” commands (removes an item from the collection).</li>
 *  <li> {@link #info()} - implements the info command (provides basic data about the collection).</li>
 *  <li> {@link #update(Long, Dragon)} - implements the update command (updates item data from the collection).</li>
 *  <li> {@link #clear()} - implements the clear command (clears the collection).</li>
 *  <li> {@link #save()} - implements the save command (saves the collection to a file).</li>
 * </ul>
 */
public class CollectionManager {
    public static TreeSet<Dragon> dragons = new TreeSet<>();
    protected static Date date;
    protected final FileDao fileDao = FileDao.getInstance();
    public void add(Dragon dragon){
        dragons.add(dragon);
    }

    public void remove(Dragon dragon){
        dragons.remove(dragon);
    }

    public StringJoiner info(){
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Information about the collection.");
        stringJoiner.add("Type: Dragon");
        stringJoiner.add("Date of initialization: " + date);
        stringJoiner.add("Quantity of elements: " + dragons.size());
        return stringJoiner;
    }

    public Response update(Long id, Dragon request){
        for (Dragon dragon : dragons){
            if (dragon.getId().equals(id)){
                dragon.setName(request.getName());
                dragon.setAge(request.getAge());
                dragon.setCharacter(request.getCharacter());
                dragon.setColor(request.getColor());
                dragon.setCoordinateX(request.getCoordinates().getX());
                dragon.setCoordinateY(request.getCoordinates().getY());
                dragon.setDepthCave(request.getCave().getDepth());
                dragon.setNumberOfTreasures(request.getCave().getNumberOfTreasures());
                dragon.setColor(request.getColor());
                return new Response("The element was successfully updated.");
            }
        }
        return new Response("The element, whose id = " + id + " wasn't found.");
    }

    public void clear(){
        dragons.clear();
        Dragon.setFreeId();
    }
    public void save(){
        fileDao.save(dragons);
    }

    public boolean isCollectionEmpty(){
        return dragons.isEmpty();
    }

}
