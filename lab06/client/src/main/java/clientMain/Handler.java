package clientMain;

import client.ReadData;
import collection.Dragon;
import exceptions.InvalidFileException;
import transfer.Request;

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

    public Handler(String string, ReadData readData) {
        this.request = string;
        this.readData = readData;
    }

    public Request getRequest() throws InterruptedException {
        ArrayList<Dragon> dragons = new ArrayList<>();
        String[] input = request.trim().split("\\s+", 2);
        String commandStr = input[0];
        String[] args = input.length > 1 ? input[1].trim().split("\\s+") : new String[0];
        try {
            switch (commandStr) {
                case "execute_script" -> {

                    if (!pathStack.contains(args[0])) {
                        pathStack.push(args[0]);
                    } else {
                        pathStack.pop();
                        throw new InvalidFileException("Обнаружена рекурсия в скрипте.");
                    }

                    Execute_script executeScript = new Execute_script(args[0]);
                }
                case "exit" -> System.exit(0);
                case "update", "add", "add_if_max", "remove_greater", "remove_lower" -> {
                    dragons.add(readData.get());
                    return new Request(commandStr, args, dragons);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


        return new Request(commandStr, args, dragons);
    }
}
