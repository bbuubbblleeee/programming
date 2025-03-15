package collectionManager;

import java.util.Date;

/**
 * The class binds and implements loading data from a file and saving it to a collection.
 */

public class InMemoryCollection extends CollectionManager{
    public InMemoryCollection() {
        dragons = fileDao.get();
        date = new Date();
    }

}
