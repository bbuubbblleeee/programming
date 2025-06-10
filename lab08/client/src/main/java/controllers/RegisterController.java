package controllers;

import alertManager.DialogManager;
import clientMain.ClientMain;
import collection.Dragon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import transfer.Request;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private ComboBox<String> languageBox;

    private Runnable callAuth;

    @FXML
    void signUp(){
        if (passwordField.getText().isBlank() || loginField.getText().isBlank()){
            DialogManager.createErrorAlert("Логин и пароль не могут быть пустыми!");
        }
        else {
            //отправка логина и пароля на сервер и проверка
            try {
                //TODO привязать логин к полю текущего пользователя в main (? copy paste)
                String[] args = new String[2];
                args[0] = loginField.getText();
                args[1] = passwordField.getText();
                String response = ClientMain.sendAndGetResponse(new Request("sign_up", args, new ArrayList<Dragon>(), "login", "password"));
                if (this.successfulRegistration(response)){
                    DialogManager.createInfoAlert("Пользователь зарегистрирован.\nВойдите в аккаунт.");
                    callAuth.run();
                }
                else{
                    DialogManager.createErrorAlert(response);
                }
            }
            catch (IOException ioException){
                System.out.println(ioException.getMessage());
                DialogManager.createErrorAlert("Неуспешная регистрация");
            }
        }
    }


    private boolean successfulRegistration(String response){
        return response.equals("Пользователь успешно зарегистрирован.");
    }

    public void setCallAuth(Runnable callAuth) {
        this.callAuth = callAuth;
    }


}
