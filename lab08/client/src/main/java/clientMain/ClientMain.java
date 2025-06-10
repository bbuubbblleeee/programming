package clientMain;

import alertManager.DialogManager;
import collection.Dragon;
import controllers.AuthController;
import controllers.EditController;
import controllers.MainController;
import controllers.RegisterController;
import exceptions.InvalidFileException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transfer.Request;

import java.io.IOException;
import java.util.function.Consumer;

public class ClientMain extends Application {
    private static final Client client = new Client();
    private static String login = null;
    private static String password = null;
    private Stage mainStage;
    private final Stage editStage = new Stage();

    public static void main(String[] args) {
//        MainController mainController = new MainController();
//        mainController.setClient(client);
//        System.out.println(mainController.getDragons());

        launch(args);
//        System.out.println("Приложение запущено.\nВведите enter {login} {password} для авторизации существующего аккаунта.\nИли зарегестрируйте аккаунт, введя sign_up {login} {password}.");
//        try (MyReader myReader = new ConsoleReader(); ReadData readData = new ReadData()) {
//            while (myReader.hasNextLine()) {
//                Handler handler = new Handler(myReader.readLine(), readData);
//                Request request = handler.getRequest();
//                if (request != null) {
//                    String response;
//                    try {
//                        response = client.sendAndGetResponse(request);
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
    public static void setLogin(String newLogin){
        login = newLogin;
    }
    public static void setPassword(String newPassword){
        password = newPassword;
    }




    private static boolean successfulEntering(String response){
        return response.equals("Пользователь успешно подключен к базе данных.");
    }




    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        startAuth();
    }

    public void startMain() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("main.fxml"));
            mainStage.setScene(new Scene(fxmlLoader.load()));
            MainController mainController = fxmlLoader.getController();
            mainController.setCallEdit(() -> startEdit(mainController.getGetDragon()));
            mainStage.show();
        }
        catch (IOException e){

        }
    }

    public void startAuth(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("auth.fxml"));
            mainStage.setScene(new Scene(fxmlLoader.load()));
            mainStage.show();
            AuthController authController = fxmlLoader.getController();
            authController.setCallMain(this::startMain);
            authController.setCallRegister(this::startRegister);
        }
        catch (IOException ioException){
            //TODO alert
        }
    }

    public void startRegister() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("registration.fxml"));
            mainStage.setScene(new Scene(fxmlLoader.load()));
            mainStage.show();
            RegisterController registerController = fxmlLoader.getController();
            registerController.setCallAuth(this::startAuth);

        }
        catch (IOException e){

        }
    }

    public void startEdit(Consumer<Dragon> getDragon) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("edit.fxml"));
            editStage.setScene(new Scene(fxmlLoader.load()));
            EditController editController = fxmlLoader.getController();
            editController.setGetDragon(getDragon);
            editController.setOnClose();
            editStage.showAndWait();
        }
        catch (IOException e){
            //TODO ну че нибудь с этим сделать, не красиво
            DialogManager.createErrorAlert("");
            throw new InvalidFileException("Не найден fxml файл.");
        }
    }


    private void closeStage(){
        mainStage.close();
    }

}