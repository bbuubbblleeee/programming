package invoker;

import client.ReadData;
import collection.Dragon;
import commands.Command;
import commands.ExecuteScript;
import exceptions.InvalidCommandException;
import exceptions.WrongNumberOfArguments;
import io.FileReader;
import transfer.Request;

import java.util.ArrayList;

import static invoker.CommandsStorage.commands;

/**
 * Класс является обработчиком пользовательского запроса.
 * <p>
 * Класс парсит введенную пользователем команду, проверяет количество и корректность аргументов
 * и создает объект {@link Request}, содержащий команду, аргументы и список объектов {@link Dragon}, если они требуются.
 * </p>
 */
public class Handler {
    private final String requestString;
    private final ReadData readData;
    public Handler(String string, ReadData readData){
        requestString = string;
        this.readData = readData;
    }

    public Request getRequest() throws WrongNumberOfArguments, InvalidCommandException, InterruptedException {
        ArrayList<Dragon> dragons = new ArrayList<>();
        String[] input = requestString.trim().split("\\s+", 2);
        Command command = findCommand(input[0]);
        String[] args = input.length > 1 ? input[1].trim().split("\\s+") : new String[0];
        if (args.length != command.getRequiredArgs()){
            throw new WrongNumberOfArguments();
        }
        long dragonNeed = command.getRequiredDragon();
        while (dragonNeed > 0){
            dragons.add(readData.get());
            dragonNeed--;
        }
        return new Request(command, args, dragons);
    }

    private Command findCommand(String commandString) {
        Command command = commands.get(commandString);
        if (command == null){
            throw new InvalidCommandException();
        }
        return command;
    }

}
