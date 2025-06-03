package clientMain;

import client.ReadData;

import io.ConsoleReader;
import io.MyReader;
import transfer.Request;

import java.io.IOException;

public class ClientMain {
    private static final Client client = new Client();
    private static String login = null;
    private static String password = null;

    public static void main(String[] args) {
        System.out.println("Приложение запущено.\nВведите enter {login} {password} для авторизации существующего аккаунта.\nИли зарегестрируйте аккаунт, введя sign_up {login} {password}.");
        try (MyReader myReader = new ConsoleReader(); ReadData readData = new ReadData()) {
            while (myReader.hasNextLine()) {
                Handler handler = new Handler(myReader.readLine(), readData);
                Request request = handler.getRequest();
                if (request != null) {
                    String response;
                    try {
                        response = sendAndGetResponse(request);
                        if (request.command().equals("enter") && successfulEntering(response)){
                            login = request.args()[0];
                            password = request.args()[1];
                        }
                        System.out.println(response);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String sendAndGetResponse(Request request) throws IOException {
        try {
            client.sendRequest(request);
            return client.recieveResponse().toString();
        }
        catch (Exception e){
            throw new IOException("Сервер временно недоступен");
        }
    }
    public static Client getClient(){
        return client;
    }
    public static String getLogin(){
        return login;
    }
    public static String getPassword(){
        return password;
    }



    private static boolean successfulEntering(String response){
        return response.equals("Пользователь успешно подключен к базе данных.");
    }
}