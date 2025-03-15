package client;

import invoker.Handler;
import invoker.Invoker;
import transfer.Request;
import transfer.Response;

/*
    Класс отвечает за логику и выполнение команды.
    <p>
    Выводит в консоль результат выполнения команды.
 */
public class Console {
    public Console(String string, ReadData readData){
        try {
            Handler handler = new Handler(string, readData);
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
