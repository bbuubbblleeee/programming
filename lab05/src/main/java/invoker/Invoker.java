package invoker;

import transfer.Request;
import transfer.Response;

public class Invoker {

    public Response executeCommand(Request request){
        return request.command().execute(request);
    }
}
