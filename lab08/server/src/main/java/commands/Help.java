package commands;

import transfer.Request;
import transfer.Response;

import java.util.StringJoiner;
import java.util.stream.Collectors;

import static invoker.CommandsStorage.commands;

/**
 * The class implements the help command.
 * The command outputs help for available commands.
 */
public class Help extends Command {
    public Help() {
        super("help", "выводит справку по доступным командам", 0);
    }

    @Override
    public Response execute(Request request) {
        String string = "Доступные команды:\n" + commands.values().stream().map(command -> "- " + command.getName() + ": " + command.getPurpose()).collect(Collectors.joining("\n"));
        return new Response(string);
    }
}
