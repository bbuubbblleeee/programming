package commands;

import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;

public class Enter extends Command{

    public Enter(){
        super("enter", "осуществляет вход в уже существующий аккаунт", 2);
    }

    @Override
    public Response execute(Request request) {
        String login = request.args()[0];
        String password = request.args()[1];
        try {
            getCollectionManager().checkUser(login, password);
            return new Response("AuthSuccess");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }
    }
}
