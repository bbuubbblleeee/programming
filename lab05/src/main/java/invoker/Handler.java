package invoker;

import client.ReadData;
import collection.Dragon;
import commands.Command;
import commands.ExecuteScript;
import exceptions.InvalidCommandException;
import exceptions.WrongNumberOfArguments;
import transfer.Request;

import java.util.ArrayList;

import static invoker.CommandsStorage.commands;

public class Handler {
    private final String requestString;
    private boolean inScript;

    public Handler(String string){
        requestString = string;
        inScript = false;
    }

    public Request getRequest() throws WrongNumberOfArguments, InvalidCommandException, InterruptedException {
        ArrayList<Dragon> dragons = new ArrayList<>();
        String[] input = requestString.trim().split("\\s+", 2);
        Command command = findCommand(input[0]);
        String[] args = input.length > 1 ? input[1].trim().split("\\s+") : new String[0];
        ReadData readData;
        if (command.getClass() == ExecuteScript.class){
            inScript = true;
            readData = new ReadData(args[0]);
        }
        else{
            readData = new ReadData();
        }
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

    private boolean ifInScript(String string){
        String[] input = string.trim().split("\\s+",2);
        return input[0].equals("execute_script");
    }

}
