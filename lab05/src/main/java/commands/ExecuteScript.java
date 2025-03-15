package commands;

import client.Console;
import client.ReadData;
import exceptions.InvalidFileException;
import transfer.Request;
import transfer.Response;

/**
 * The class implements the execute_script command.
 * The command reads and executes a script from the specified file.
 */
public class ExecuteScript extends Command{
    public ExecuteScript(){
        super("execute_script", "reads and executes a script from the specified file.", 1, 0);
    }

    @Override
    public Response execute(Request request) {
        ReadData readData;
        try {
            readData = new ReadData(request.args()[0]);
        }
        catch (InvalidFileException e){
            return new Response(e.getMessage());
        }
        while (readData.myReader.hasNextLine()){
            String command = readData.myReader.readLine();
            System.out.println("Command: " + command + ".");
            new Console(command, readData);
        }
        return new Response("Script was successfully executed.");
    }
}
