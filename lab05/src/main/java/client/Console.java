package client;

import collectionManager.CollectionManager;
import exceptions.InvalidFileException;
import invoker.Handler;
import invoker.Invoker;
import transfer.Request;
import transfer.Response;

import java.util.Stack;

/**
 * The class is responsible for the logic and execution of the command.
 * Outputs the result of the command execution to the console.:
 **/
public class Console {
    private static final Stack<String> pathStack = new Stack<>();
    public Console(String string, ReadData readData, CollectionManager collectionManager){
        try {
            Handler handler = new Handler(string, readData, collectionManager);
            Request request = handler.getRequest();
            if (request.command().getName().equals("execute_script")){
                if (!pathStack.contains(request.args()[0])) {
                    pathStack.push(request.args()[0]);
                }
                else{
                    pathStack.pop();
                    throw new InvalidFileException("Обнаружена рекурсия в скрипте.");
                }
            }
            Invoker invoker = new Invoker();
            Response response = invoker.executeCommand(request);
            System.out.print(response.toString());
            System.out.println("\n");
        }
        catch (InvalidFileException e){
            throw new InvalidFileException(e.getMessage());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
