package collection;

import com.google.gson.annotations.SerializedName;

import static collection.checkers.CollectionChecker.numberOfTreasuresChecker;

/**
 * Cave class.
 */
public class DragonCave {
    @SerializedName("Глубина")
    private float depth;

    @SerializedName("Количество сокровищ")
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
        return "(" +
                "Глубина = " + depth + ", " +
                "Количество сокровищ = " + numberOfTreasures +
                ")";
    }
}


