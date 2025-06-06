package clientMain;

import client.ReadData;

import io.ConsoleReader;
import io.MyReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transfer.Request;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ClientMain extends Application {
    private static final Client client = new Client();
    private static String login = null;
    private static String password = null;

    public static void main(String[] args) {
        launch(args);
//        System.out.println("Приложение запущено.\nВведите enter {login} {password} для авторизации существующего аккаунта.\nИли зарегестрируйте аккаунт, введя sign_up {login} {password}.");
//        try (MyReader myReader = new ConsoleReader(); ReadData readData = new ReadData()) {
//            while (myReader.hasNextLine()) {
//                Handler handler = new Handler(myReader.readLine(), readData);
//                Request request = handler.getRequest();
//                if (request != null) {
//                    String response;
//                    try {
//                        response = sendAndGetResponse(request);
//                        if (request.command().equals("enter") && successfulEntering(response)){
//                            login = request.args()[0];
//                            password = request.args()[1];
//                        }
//                        System.out.println(response);
//                    }
//                    catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
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

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("auth.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
}