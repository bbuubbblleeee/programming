package collection.id;

import collectionManager.CollectionManager;
import exceptions.InvalidFileException;

public class IdGenerator {
    private static Long freeId;
    private final CollectionManager collectionManager = new CollectionManager();

    public Long generateId(){
        try{
            freeId = collectionManager.getLastId();
            freeId++;
            collectionManager.saveLastId(freeId);
            return freeId;
        }
        catch (Exception e){
            throw new InvalidFileException(e.getMessage());
        }
    }
}
