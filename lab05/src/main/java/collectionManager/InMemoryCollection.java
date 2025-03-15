package collectionManager;

import collection.Dragon;
import dao.FileDao;
import main.Main;

import java.util.Date;

/**
 * Класс связывает и реализует загрузку данных из файла и их сохранение в коллекцию.
 */

public class InMemoryCollection extends CollectionManager{
    public InMemoryCollection() {
        dragons = fileDao.get();
        date = new Date();
    }

}
