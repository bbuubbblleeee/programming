package collectionManager;


import collection.*;

import dao.FileDao;
import transfer.Response;


import java.util.*;

/**
 * Класс хранит коллекцию и содержит методы, реализующие команды, работающие непосредственно с коллекцией.
 * Методы, реализующие команды:
 * <ul>
 *  <li> {@link #add(Dragon)} - реализует команду add (добавляет элемент в коллекцию).</li>
 *  <li> {@link #remove(Dragon)} - реализует команды "remove%" (удаляет элемент из коллекции).</li>
 *  <li> {@link #info()} - реализует команду info (предоставляет основные данные о коллекции).</li>
 *  <li> {@link #update(Long, Dragon)} - реализует команду update (обновляет данные элемента из коллекции).</li>
 *  <li> {@link #clear()} - реализует команду clear (очищает коллекцию).</li>
 *  <li> {@link #save()} - реализует команду save (сохраняет коллекцию в файл).</li>
 *</ul>
 */
public class CollectionManager {
    public static TreeSet<Dragon> dragons = new TreeSet<Dragon>();
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
