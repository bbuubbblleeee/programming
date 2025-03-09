package collection;

import static collection.checkers.CollectionChecker.*;

public class Coordinates {
    private long x; //Значение поля должно быть больше -28
    private long y;

    public Coordinates(long x, long y){
        xChecker(x);
        this.x = x;
        this.y = y;
    }
    public void setX(long x){
        xChecker(x);
        this.x = x;
    }
    public void setY(long y){
        this.y = y;
    }

    public long getX(){
        return this.x;
    }

    public long getY(){
        return this.y;
    }
    public String toString(){
        return "{" +
                "x = " + x + ", " +
                "y = " + y +
                "}";
    }
}
