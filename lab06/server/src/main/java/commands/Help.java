package commands;

import transfer.Request;
import transfer.Response;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static invoker.CommandsStorage.commands;

/**
 * The class implements the help command.
 * The command outputs help for available commands.
 */
public class Help extends Command {
    public Help() {
        super("help", "выводит справку по доступным командам", 0, 0);
    }

    @Override
    public Response execute(Request request) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Доступные команды:");
        String string = commands.values().stream().map(command -> "- " + command.getName() + ": " + command.getPurpose()).collect(Collectors.joining("\n"));
//        for (Command command : commands.values()) {
//            stringJoiner.add(" - " + command.getName() + ": " + command.getPurpose());
//        }
        return new Response(string);
    }
}
