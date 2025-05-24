package commands;

import transfer.Request;
import transfer.Response;

import static server.ServerMain.getCollectionManager;

public class SignUp extends Command{
    public SignUp() {
        super("sign_up", "Регистрирует нового пользователя.", 2, 0);
    }


    @Override
    public Response execute(Request request) {
        String login = request.args()[0];
        String password = request.args()[1];
        try {
            getCollectionManager().signUp(login, password);
            return new Response("Пользователь успешно зарегистрирован.");
        }
        catch (Exception e){
            return new Response(e.getMessage());
        }
    }

}
