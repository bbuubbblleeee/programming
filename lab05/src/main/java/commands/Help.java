package commands;

import transfer.Request;
import transfer.Response;

import java.util.StringJoiner;

import static invoker.CommandsStorage.commands;
/**
 * Класс реализует команду help.
 * Команда выводит справку по доступным командам.
 */
public class Help extends Command{
    public Help(){
        super("help", "displays all available commands.", 0, 0);
    }
    @Override
    public Response execute(Request request) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Available commands:");
        for (Command command : commands.values()){
            stringJoiner.add(" - " + command.getName() + ": " + command.getPurpose());
        }
        return new Response(stringJoiner.toString());
    }
}
