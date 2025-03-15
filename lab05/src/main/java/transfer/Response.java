package transfer;

import collection.Dragon;

/**
 * Класс предоставляет ответ на выполнение команды.
 * Класс хранит сообщения о результате выполнения команды.
 */
public record Response (String message) {
    @Override
    public String toString(){
        return message;
    }
}
