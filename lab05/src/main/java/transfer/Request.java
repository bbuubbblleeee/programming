package transfer;

import collection.Dragon;
import commands.Command;

import java.util.ArrayList;

/**
 * Класс создает запрос на выполнение команды.
 * <p>
 * Запрос содержит в себе информацию о команде, ее аргументах и списке объектов {@link Dragon}, если они требуются.
 */
public record Request(Command command, String[] args, ArrayList<Dragon> dragons) {
}
