package collection.checkers;

import collection.Coordinates;
import collection.DragonCave;
import collection.DragonType;

public class CollectionChecker extends ArgumentChecker{
    public static void nameChecker(String name){
        nullChecker(name);
        argumentChecker(!name.isEmpty(), "Argument can't be empty.");
    }

    public static void xChecker(Long x){
        argumentChecker(x > - 28, "Invalid value.\nExpected value > -28.");
    }
    public static void coordinatesChecker(Coordinates coordinates){
        nullChecker(coordinates);
    }
    public static void caveChecker(DragonCave cave){
        nullChecker(cave);
    }
    public static void ageChecker(Integer age){
        nullChecker(age);
        argumentChecker(age > 0, "Invalid value.\nExpected value > 0.");
    }

    public static void typeChecker(DragonType type){
        nullChecker(type);
    }

    public static void numberOfTreasuresChecker(int numberOfTreasures){
        argumentChecker(numberOfTreasures > 0, "Invalid value.\nExpected value > 0.");

    }
}
