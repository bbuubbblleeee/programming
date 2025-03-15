package collectionManager;

import java.util.Date;

/**
 * The class binds and implements loading data from a file and saving it to a collection.
 */

public class InMemoryCollection extends CollectionManager{
    public InMemoryCollection() {
        try {
            dragons = fileDao.get();
        }
        catch (Exception e){
            System.out.println("Invalid file input.");
        }
        date = new Date();
    }

}
