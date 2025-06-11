package io;

import exceptions.EndOfInputException;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * An implementation of {@link MyReader} that reads input from the console.
 * <p>
 * Uses {@link Scanner} to read strings from the standard input stream.
 * Throws an {@link EndOfInputException} when the end of input is reached.
 * </p>
 */
public class ConsoleReader implements MyReader {
    private final Scanner scanner;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
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
            throw new EndOfInputException("Конец ввода.");
        }
    }

    @Override
    public String readLine(String string) {
        try {
            System.out.print("Введите " + string + ": ");
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException("Конец ввода");
        }
    }

    @Override
    public void close() throws Exception {
        this.scanner.close();
    }
}
