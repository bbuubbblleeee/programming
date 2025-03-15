package collection.checkers;

import collection.DragonType;
import exceptions.WrongArgumentException;
/**
 * The class contains methods to check all the required values of the arguments of the class object {@link collection.Dragon}.
 * <p>
 *     The checks include:
 *     <ul>
 *         <li> {@link #nameChecker(String)} - The name cannot be null or an empty string.
 *         <li> {@link #nameChecker(String)} - The age must be greater than 0.
 *         <li> {@link #xChecker(Long)} - The x coordinate must be greater than -28.
 *         <li> {@link #yChecker(Long)} - The y coordinate cannot be null.
 *         <li> {@link #typeChecker(DragonType)} - The type cannot be null.
 *         <li> {@link #numberOfTreasuresChecker(int)} - The number of treasures must be greater than 0.
 *         </ul>
 *         Наследуется от {@link ArgumentChecker} и использует его базовые проверки.
 **/
public class CollectionChecker extends ArgumentChecker{
    public static void nameChecker(String name) throws WrongArgumentException {
        nullChecker(name);
        argumentChecker(!name.isEmpty(), "Argument can't be empty.");
    }

    public static void ageChecker(Integer age) throws WrongArgumentException{
        nullChecker(age);
        argumentChecker(age > 0, "Invalid value.\nExpected value > 0.");
    }

    public static void xChecker(Long x) throws WrongArgumentException{
        nullChecker(x);
        argumentChecker(x > - 28, "Invalid value.\nExpected value > -28.");
    }
    public static void yChecker(Long y) throws WrongArgumentException{
        nullChecker(y);
    }

    public static void typeChecker(DragonType type) throws WrongArgumentException{
        nullChecker(type);
    }

    public static void numberOfTreasuresChecker(int numberOfTreasures) throws WrongArgumentException{
        argumentChecker(numberOfTreasures > 0, "Invalid value.\nExpected value > 0.");
    }
}
