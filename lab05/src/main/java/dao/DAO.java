package dao;

import collection.Dragon;
import com.sun.source.tree.Tree;

import java.util.TreeSet;

public interface DAO {
    String getPath();
    TreeSet<Dragon> get();
    void save(TreeSet<Dragon> dragons);
}
