package io;

import exceptions.EndOfInputException;
import exceptions.InvalidFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * An implementation of {@link MyReader} that reads strings from a file.
 * <p>
 * Uses {@link Scanner} to read lines from the specified file.
 * Throws {@link InvalidFileException} or {@link EndOfInputException} when errors occur while opening or reading a file.
 * </p>
 */
public class FileReader implements MyReader {
    private final Scanner scanner;

    public FileReader(String filePath) throws InvalidFileException {
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("Файл не найден.");
        } catch (Exception b) {
            throw new InvalidFileException("Неверный скрипт.");
        }
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException("Конец файла.");
        }
    }

    @Override
    public String readLine(String string) {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException("Конец файла");
        }
    }

    @Override
    public void close() throws Exception {
        this.scanner.close();
    }
}
