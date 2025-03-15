package main;

import client.Console;
import client.ReadData;
import io.ConsoleReader;
import io.MyReader;

/**
 * Класс запускает выполнение программы.
 */
public class Main {
    public static void main(String[] args) {
        MyReader myReader = new ConsoleReader();
        ReadData readData = new ReadData();
        while(myReader.hasNextLine()){
            new Console(myReader.readLine(), readData);
        }
    }
}