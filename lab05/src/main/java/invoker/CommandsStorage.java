package invoker;

import commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandsStorage {
    public static final HashMap<String, Command> commands = new HashMap<>(Map.ofEntries(
            Map.entry("add", new Add()),
            Map.entry("add_if_max", new AddIfMax()),
            Map.entry("clear", new Clear()),
            Map.entry("count_by_age", new CountByAge()),
            Map.entry("exit", new Exit()),
            Map.entry("help", new Help()),
            Map.entry("info", new Info()),
            Map.entry("print_field_descending_character", new PrintFieldDescendingCharacter()),
            Map.entry("remove_any_by_age", new RemoveAnyByAge()),
            Map.entry("remove_by_id", new RemoveById()),
            Map.entry("remove_greater", new RemoveGreater()),
            Map.entry("remove_lower", new RemoveLower()),
            Map.entry("show", new Show()),
            Map.entry("update", new Update()),
            Map.entry("save", new Save()),
            Map.entry("execute_script", new ExecuteScript())
    ));
}
