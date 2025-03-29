package client;

import collectionManager.CollectionManager;
import invoker.Handler;
import invoker.Invoker;
import transfer.Request;
import transfer.Response;

/**
 * The class is responsible for the logic and execution of the command.
 * Outputs the result of the command execution to the console.:
 **/
public class Console {
    public Console(String string, ReadData readData, CollectionManager collectionManager){
        try {
            Handler handler = new Handler(string, readData, collectionManager);
            Request request = handler.getRequest();
            Invoker invoker = new Invoker();
            Response response = invoker.executeCommand(request);
            System.out.print(response.toString());
            System.out.println("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
