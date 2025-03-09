package exceptions;

public class WrongNumberOfArguments extends RuntimeException {
    public WrongNumberOfArguments() {
        super("Wrong number of arguments.");
    }
}
