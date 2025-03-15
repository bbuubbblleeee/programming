package commands;

import transfer.Request;
import transfer.Response;

/**
 * The class implements the exit command.
 * The command terminates the program.
 */
public class Exit extends Command{
    public Exit(){
        super("exit", "ends the program", 0, 0);
    }

    @Override
    public Response execute(Request request) {
        System.exit(0);
        return new Response("The program is ending...");
    }
}
