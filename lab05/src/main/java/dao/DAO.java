package dao;

import collection.Dragon;

import java.util.TreeSet;

/**
 * Interface for working with files and databases.
 */
public interface DAO {
    TreeSet<Dragon> get();
    void save(TreeSet<Dragon> dragons);
}
