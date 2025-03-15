package exceptions;

/**
 * Исключение, указывающее на некорректное количество введённых аргументов.
 */
public class WrongNumberOfArguments extends RuntimeException {
    public WrongNumberOfArguments() {
        super("Wrong number of arguments.");
    }
}
