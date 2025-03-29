package collection.checkers;

import exceptions.WrongArgumentException;
/**
 * The class is responsible for checking and validating arguments entered by the user.
 **/
public class ArgumentChecker {
    public static void nullChecker (Object object) throws WrongArgumentException{
        if (object == null || object.equals("")){
            throw new WrongArgumentException("Аргумент не может быть нулевым.");
        }
    }
    public static void argumentChecker (boolean statement, String message) throws WrongArgumentException {
        if (!statement){
            throw new WrongArgumentException(message);
        }
    }
}
