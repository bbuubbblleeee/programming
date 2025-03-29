package collectionManager;

import dao.DateAndDragons;

/**
 * The class binds and implements loading data from a file and saving it to a collection.
 */

public class InMemoryCollection extends CollectionManager{
    public InMemoryCollection() {
        try {
            DateAndDragons dateAndDragons = fileDao.get();
            date = dateAndDragons.date();
            dragons = dateAndDragons.dragons();
            if (!dragons.isEmpty()) {
                saveLastId(dragons.last().getId());
            }
            else{
                saveLastId(1L);
            }
            System.out.println("Коллекция успешно считана из файла.");
        }
        catch (Exception e){
            System.out.println("Недопустимый файл.");
        }
    }

}
