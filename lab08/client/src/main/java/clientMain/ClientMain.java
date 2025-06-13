package clientMain;

import alertManager.DialogManager;
import collection.Dragon;
import controllers.AuthController;
import controllers.EditController;
import controllers.MainController;
import controllers.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import languages.ErrorLocalizator;
import languages.Localizator;
import transfer.Request;

import java.io.IOException;
import java.util.function.Consumer;

public class ClientMain extends Application {
    private static final Client client = new Client();
    private static String login = null;
    private static String password = null;
    private Stage mainStage;
    private final Stage editStage = new Stage();
    private static final Localizator errorLocalizator = ErrorLocalizator.getInstance();


    public static void main(String[] args) {
        launch(args);
    }

    public static String sendAndGetResponse(Request request) throws IOException {
        try {
            client.sendRequest(request);
            return client.recieveResponse().toString();
        }
        catch (Exception e){
            throw new IOException(errorLocalizator.getString("ServerUnavailable"));
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
            mainController.setCallEdit((dragon) -> startEdit(dragon, mainController.getGetDragon()));
            mainController.refresh();
            mainStage.show();
        }
        catch (IOException e){
            DialogManager.createErrorAlert(errorLocalizator.getString("FileNotFound"));
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
            DialogManager.createErrorAlert(errorLocalizator.getString("FileNotFound"));
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
            DialogManager.createErrorAlert(errorLocalizator.getString("FileNotFound"));
        }
    }

    public void startEdit(Dragon dragon, Consumer<Dragon> getDragon) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("edit.fxml"));
            editStage.setScene(new Scene(fxmlLoader.load()));
            EditController editController = fxmlLoader.getController();
            editController.setGetDragon(getDragon);
            editController.setOnClose();
            editController.fillFields(dragon);
            editStage.showAndWait();
        }
        catch (IOException e){
            DialogManager.createErrorAlert(errorLocalizator.getString("FileNotFound"));
        }
    }


    private void closeStage(){
        mainStage.close();
    }

}