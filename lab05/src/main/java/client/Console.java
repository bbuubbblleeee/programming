package client;

import invoker.Handler;
import invoker.Invoker;
import transfer.Request;
import transfer.Response;

public class Console {
    public Console(String string){
        try {
            Handler handler = new Handler(string);
            Request request = handler.getRequest();
            Invoker invoker = new Invoker();
            Response response = invoker.executeCommand(request);
            System.out.println(response.toString());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
