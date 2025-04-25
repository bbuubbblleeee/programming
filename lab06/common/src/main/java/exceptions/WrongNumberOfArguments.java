package exceptions;

/**
 * Exception indicating an invalid number of arguments entered.
 */
public class WrongNumberOfArguments extends RuntimeException {
    public WrongNumberOfArguments() {
        super("Введено неверное количество аргументов.");
    }
}
