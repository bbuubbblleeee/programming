package main;

import client.Console;
import io.ConsoleReader;
import io.MyReader;

public class Main {
    public static void main(String[] args) {
        MyReader myReader = new ConsoleReader();
        while(myReader.hasNextLine()){
            new Console(myReader.readLine());
        }
    }
}