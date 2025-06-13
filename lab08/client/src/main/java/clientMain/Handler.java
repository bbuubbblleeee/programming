package clientMain;

import client.ReadData;

import collection.Dragon;
import exceptions.InvalidFileException;
import languages.ErrorLocalizator;
import languages.Localizator;
import transfer.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


/**
 * The class is the handler of a user request.
 * <p>
 * The class parses the command entered by the user, checks the number and correctness of arguments
 * and creates a {@link Request} object containing the command, arguments and a list of {@link Dragon} objects, if required.
 * </p>
 */
public class Handler {
    private static final Stack<String> pathStack = new Stack<>();
    private final String request;
    private final ReadData readData;
    private static final Localizator errorLocalizator = ErrorLocalizator.getInstance();


    public Handler(String string, ReadData readData) {
        this.request = string;
        this.readData = readData;
    }

    public Request getRequest() throws InvalidFileException {
        ArrayList<Dragon> dragons = new ArrayList<>();
        String[] input = request.trim().split("\\s+", 2);
        String commandStr = input[0];
        String[] args = input.length > 1 ? input[1].trim().split("\\s+") : new String[0];
        try {
            switch (commandStr) {
                case "execute_script" -> {
                    if (args.length == 0){
                        throw new InvalidFileException(errorLocalizator.getString("ScriptPath"));
                    }
                    if (!pathStack.contains(input[1])) {
                        pathStack.push(input[1]);
                    } else {
                        throw new InvalidFileException(errorLocalizator.getString("ScriptRecursion"));
                    }

                    Execute_script executeScript = new Execute_script();
                    executeScript.execute(input[1]);
                    pathStack.pop();
                    return null;
                }
                case "exit" -> {
                    ClientMain.getClient().close();
                    System.exit(0);
                }
                case "update", "add", "add_if_max", "remove_greater", "remove_lower" -> {
                    dragons.add(readData.get());
                    return new Request(commandStr, args, dragons, ClientMain.getLogin(), ClientMain.getPassword());
                }
                case "enter", "sign_up" -> {
                    return new Request(commandStr, args, dragons, "login", "password");
                }
            }
        } catch (IOException | InterruptedException | InvalidFileException e) {
            throw new InvalidFileException(e.getMessage());
        }
        return new Request(commandStr, args, dragons, ClientMain.getLogin(), ClientMain.getPassword());
    }

    public static void setStack(String path){
        pathStack.push(path);
    }
}
