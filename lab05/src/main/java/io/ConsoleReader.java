package io;

import exceptions.EndOfInputException;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Реализация {@link MyReader}, которая считывает ввод с консоли.
 * <p>
 * Использует {@link Scanner} для чтения строк из стандартного потока ввода.
 * При достижении конца ввода выбрасывает {@link EndOfInputException}.
 * </p>
 */
public class ConsoleReader implements MyReader {
    private final Scanner scanner;
    public ConsoleReader(){
        scanner = new Scanner(System.in);
    }
    @Override
    public boolean hasNextLine(){
        return scanner.hasNextLine();
    }

    @Override
    public String readLine(){
        try{
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException("input");
        }
    }

    @Override
    public String readLine(String string){
        try{
            System.out.print("Enter the " + string + ": ");
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException("input");
        }
    }

}
