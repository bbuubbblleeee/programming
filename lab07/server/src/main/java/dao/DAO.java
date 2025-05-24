package dao;

import collection.Dragon;

import java.util.Set;

/**
 * Interface for working with files and databases.
 */
public interface DAO {
    DateAndDragons get();

    void save(Set<Dragon> dragons);
}
