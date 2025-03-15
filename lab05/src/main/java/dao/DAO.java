package dao;

import collection.Dragon;

import java.util.TreeSet;

/**
 * Интерфейс для работы с хранилищем данных.
 */
public interface DAO {
    String getPath();
    TreeSet<Dragon> get();
    void save(TreeSet<Dragon> dragons);
}
