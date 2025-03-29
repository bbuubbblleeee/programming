package dao;

import collection.Dragon;

import java.nio.file.Path;
import java.util.TreeSet;

/**
 * Interface for working with files and databases.
 */
public interface DAO {
    DateAndDragons get();
    void save(TreeSet<Dragon> dragons);
}
