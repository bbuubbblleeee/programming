package invoker;

import client.ReadData;
import collection.Dragon;
import commands.Command;
import exceptions.InvalidCommandException;
import exceptions.WrongNumberOfArguments;
import transfer.Request;

import java.util.ArrayList;

import static invoker.CommandsStorage.commands;

/**
 * The class is the handler of a user request.
 * <p>
 * The class parses the command entered by the user, checks the number and correctness of arguments
 * and creates a {@link Request} object containing the command, arguments and a list of {@link Dragon} objects, if required.
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
        if (command.getName().equals("update")){
            return new Request(command, args, dragons, this.readData);
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
