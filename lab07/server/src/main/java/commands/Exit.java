package commands;

import transfer.Request;
import transfer.Response;


/**
 * The class implements the exit command.
 * The command terminates the program.
 */
public class Exit extends Command {
    //закрытие клиента реализовано на клиенте, здесь осталось только ради команды help
    public Exit() {
        super("exit", "завершает программу", 0);
    }

    //закрывает сервер
    @Override
    public Response execute(Request request) {
        System.exit(0);
        return new Response("Программа завершается...");
    }
}
