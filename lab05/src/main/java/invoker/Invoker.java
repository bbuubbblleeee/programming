package invoker;

import collectionManager.CollectionManager;
import transfer.Request;
import transfer.Response;

/**
 * The class that starts the execution of the command.
 * <p>
 * The class accepts a {@link Request} object containing the command and its parameters,
 * and executes the command, returning the result as {@link Response}.
 * </p>
 */
public class Invoker {

    public Response executeCommand(Request request){
        return request.command().execute(request);
    }
}
