package io;

/**
 * Интерфейс для чтения строк данных.
 * <p>
 * Реализован для консольного ввода {@link ConsoleReader} и для чтения из файла {@link FileReader}.
 * </p>
 */
public interface MyReader {
    boolean hasNextLine();
    String readLine();
    String readLine(String string);
}
