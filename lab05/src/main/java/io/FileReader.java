package io;

import exceptions.EndOfInputException;
import exceptions.InvalidFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileReader implements MyReader{
    private Scanner scanner;

    public FileReader(String filePath) throws InvalidFileException{
        try {
            scanner = new Scanner(new File(filePath));
        }
        catch (FileNotFoundException e){
            throw new InvalidFileException("Script file wasn't found.");
        }
        catch (Exception b){
            throw new InvalidFileException("Invalid script file.");
        }
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
            throw new EndOfInputException("file");
        }    }

    @Override
    public String readLine(String string){
        try{
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new EndOfInputException("file");
        }    }
}
