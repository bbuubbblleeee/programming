package commands;

import client.Console;
import client.ReadData;
import transfer.Request;
import transfer.Response;

/**
 * The class implements the execute_script command.
 * The command reads and executes a script from the specified file.
 */
public class ExecuteScript extends Command{
    public ExecuteScript(){
        super("execute_script", "считывает и исполняет скрипт из указанного файла.", 1, 0);
    }

    @Override
    public Response execute(Request request) {
        try {
            ReadData readData = new ReadData(request.args()[0]);
            while (readData.myReader.hasNextLine()){
                String command = readData.myReader.readLine();
                System.out.println("Текущая команда: " + command + ".");
                new Console(command, readData, request.collectionManager());
            }
            return new Response("Скрипт был исполнен.");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }
    }
}
