package collectionManager;

import dao.FileDao;
import main.Main;

import java.util.Date;


public class InMemoryCollection extends CollectionManager{
    public InMemoryCollection() {
        dragons = fileDao.get();
        date = new Date();
    }

}
