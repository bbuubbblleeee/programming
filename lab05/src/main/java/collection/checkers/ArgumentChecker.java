package collection.checkers;

import exceptions.WrongArgumentException;
/*
    Класс отвечает за проверку и валидацию аргументов, введённых пользователем.
    <p>
    @throws WrongArgumentException при некорректном вводе.
 */
public class ArgumentChecker {
    public static void nullChecker (Object object) throws WrongArgumentException{
        if (object == null || object.equals("")){
            throw new WrongArgumentException("Argument can't be null.");
        }
    }
    public static void argumentChecker (boolean statement, String message) throws WrongArgumentException {
        if (!statement){
            throw new WrongArgumentException(message);
        }
    }
}
