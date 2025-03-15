package collection;

import static collection.checkers.CollectionChecker.numberOfTreasuresChecker;

/**
 * Cave class.
 */
public class DragonCave {
    private float depth;
    private int numberOfTreasures; //Значение поля должно быть больше 0

    public DragonCave(float depth, int numberOfTreasures) {
        numberOfTreasuresChecker(numberOfTreasures);
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public void setNumberOfTreasures(int numberOfTreasures) {
        numberOfTreasuresChecker(numberOfTreasures);
        this.numberOfTreasures = numberOfTreasures;
    }

    public float getDepth() {
        return this.depth;
    }

    public int getNumberOfTreasures() {
        return this.numberOfTreasures;
    }
    public String toString(){
        return "{" +
                "depth = " + depth + ", " +
                "number of treasures = " + numberOfTreasures +
                "}";
    }
}


