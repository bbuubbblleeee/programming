package collection.checkers;

import collection.DragonType;
import exceptions.WrongArgumentException;

/**
 * The class contains methods to check all the required values of the arguments of the class object {@link collection.Dragon}.
 * <p>
 * The checks include:
 * <ul>
 *     <li> {@link #nameChecker(String)} - The name cannot be null or an empty string.
 *     <li> {@link #nameChecker(String)} - The age must be greater than 0.
 *     <li> {@link #xChecker(Long)} - The x coordinate must be greater than -28.
 *     <li> {@link #yChecker(Long)} - The y coordinate cannot be null.
 *     <li> {@link #typeChecker(DragonType)} - The type cannot be null.
 *     <li> {@link #numberOfTreasuresChecker(int)} - The number of treasures must be greater than 0.
 *     </ul>
 *     Наследуется от {@link ArgumentChecker} и использует его базовые проверки.
 **/
public class CollectionChecker extends ArgumentChecker {
    public static void nameChecker(String name) throws WrongArgumentException {
        nullChecker(name);
        argumentChecker(!name.isEmpty(), "Аргумент не может быть пустым.");
    }

    public static void ageChecker(Integer age) throws WrongArgumentException {
        nullChecker(age);
        argumentChecker(age > 0, "Недопустимое значение.\nОжидалось значение > 0.");
    }

    public static void xChecker(Long x) throws WrongArgumentException {
        nullChecker(x);
        argumentChecker(x > -28, "Недопустимое значение.\nОжидалось значение > -28.");
    }

    public static void yChecker(Long y) throws WrongArgumentException {
        nullChecker(y);
    }

    public static void typeChecker(DragonType type) throws WrongArgumentException {
        nullChecker(type);
    }

    public static void numberOfTreasuresChecker(Integer numberOfTreasures) throws WrongArgumentException {
        nullChecker(numberOfTreasures);
        argumentChecker(numberOfTreasures > 0, "Недопустимое значение.\nОжидалось значение > 0.");
    }

    public static String depthChecker(String depth) throws WrongArgumentException {
        if (depth == null || depth.isBlank()) {
            throw new WrongArgumentException("Недопустимое значение.\nОжидался аргумент типа float.");
        }
        Float depthFloat;
        try {
            depthFloat = Float.parseFloat(depth);
            if (!depthFloat.toString().replaceAll("\\.?0*$", "").equals(depth)) {
                throw new WrongArgumentException("Недопустимое значение.\nОжидался аргумент типа float.");
            }
            return depth;
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Недопустимое значение.\nОжидался аргумент типа float.");
        }
    }

}
