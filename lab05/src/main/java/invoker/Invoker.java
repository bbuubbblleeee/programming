package invoker;

import collection.Dragon;
import transfer.Request;
import transfer.Response;

/**
 * Класс запускающий выполнение команды.
 * <p>
 * Класс принимает объект {@link Request}, содержащий команду и ее параметры,
 * и выполняет команду, возвращая результат выполнения в виде {@link Response}.
 * </p>
 */
public class Invoker {

    public Response executeCommand(Request request){
        return request.command().execute(request);
    }
}
