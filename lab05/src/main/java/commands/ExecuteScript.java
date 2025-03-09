package commands;

import client.Console;
import exceptions.InvalidFileException;
import transfer.Request;
import transfer.Response;
import io.FileReader;

import java.io.File;

public class ExecuteScript extends Command{
    public ExecuteScript(){
        super("execute_script", "reads and executes a script from the specified file.", 1, 0);
    }

    @Override
    public Response execute(Request request) {
        FileReader fileReader;
        File file = new File(request.args()[0]);
        System.out.println(file.getAbsolutePath());
        try {
            fileReader = new FileReader(request.args()[0]);
        }
        catch (InvalidFileException e){
            return new Response(e.getMessage());
        }
        while (fileReader.hasNextLine()){
            new Console(fileReader.readLine());
            System.out.println("\n");
        }
        return new Response("Script was successfully executed.");
    }
}
