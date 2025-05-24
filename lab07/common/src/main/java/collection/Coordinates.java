package collection;

/**
 * Coordinates class.
 */

import java.io.Serializable;

import static collection.checkers.CollectionChecker.xChecker;

public class Coordinates implements Serializable {
    private long x; //Значение поля должно быть больше -28
    private long y;

    public Coordinates(long x, long y) {
        xChecker(x);
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return this.x;
    }

    public void setX(long x) {
        xChecker(x);
        this.x = x;
    }

    public long getY() {
        return this.y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public String toString() {
        return "(" +
                "x = " + x + ", " +
                "y = " + y +
                ")";
    }
}
