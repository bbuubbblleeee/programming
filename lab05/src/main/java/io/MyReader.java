package io;

/**
 * Interface for reading data strings.
 * <p>
 * Implemented for console input {@link ConsoleReader} and for reading from a file {@link FileReader}.
 * </p>
 */
public interface MyReader {
    boolean hasNextLine();
    String readLine();
    String readLine(String string);
}
