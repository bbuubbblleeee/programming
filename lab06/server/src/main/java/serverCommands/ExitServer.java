package serverCommands;

import serverCommands.Save;
import serverCommands.ServerCommand;
import transfer.Request;
import transfer.Response;


/**
 * The class implements the exit command.
 * The command terminates the program.
 */
public class ExitServer extends ServerCommand {

    //закрывает сервер
    @Override
    public Response execute() {
        new Save().execute();
        System.exit(0);
        return new Response("Сервер отключается...");
    }
}
