package collectionManager;

import dao.DateAndDragons;

/**
 * The class binds and implements loading data from a file and saving it to a collection.
 */

public class InMemoryCollection extends CollectionManager {
    public InMemoryCollection() {
        try {
            DateAndDragons dateAndDragons = fileDao.get();
            date = dateAndDragons.date();
            dragons = dateAndDragons.dragons();
            System.out.println("Коллекция успешно считана из файла.");
        } catch (Exception e) {
            System.out.println("Недопустимый файл.");
        }
    }

}
