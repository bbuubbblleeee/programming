package collection.checkers;

import exceptions.WrongArgumentException;

public class ArgumentChecker {
    public static void nullChecker (Object object) throws WrongArgumentException{
        if (object == null){
            throw new WrongArgumentException("Argument can't be null.\n");
        }
    }
    public static void argumentChecker (boolean statement, String message) throws WrongArgumentException {
        if (!statement){
            throw new WrongArgumentException(message);
        }
    }
}
