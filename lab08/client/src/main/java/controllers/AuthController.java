package controllers;


import alertManager.DialogManager;
import clientMain.ClientMain;
import collection.Dragon;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import transfer.Request;

import java.io.IOException;
import java.util.ArrayList;

public class AuthController {
    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox<String> languageBox;

    private Runnable callMain;
    private Runnable callRegister;

    @FXML
    void initialize(){
//        languageBox.setItems();
    }

    @FXML
    void auth(){
        if (passwordField.getText().isBlank() || loginField.getText().isBlank()){
            DialogManager.createErrorAlert("Логин и пароль не могут быть пустыми!");
        }
        else {
            //отправка логина и пароля на сервер и проверка
            try {
                //TODO привязать логин к полю текущего пользователя в main
                String[] args = new String[2];
                args[0] = loginField.getText();
                args[1] = passwordField.getText();
                String response = ClientMain.sendAndGetResponse(new Request("enter", args, new ArrayList<Dragon>(), "login", "password"));
                if (this.successfulEntering(response)){
                    ClientMain.setLogin(loginField.getText());
                    ClientMain.setPassword(passwordField.getText());
                    callMain.run();
                }
                else{
                    DialogManager.createErrorAlert(response);
                }
            }
            catch (IOException ioException){
                System.out.println(ioException.getMessage());
                DialogManager.createInfoAlert(ioException.getMessage());
                //TODO добавить alert
            }
        }
    }

    @FXML
    void register(){
        callRegister.run();
    }

    public void setCallMain(Runnable callMain){
        this.callMain = callMain;
    }
    public void setCallRegister(Runnable callRegister){
        this.callRegister = callRegister;
    }



    private boolean successfulEntering(String response){
        return response.equals("Пользователь успешно подключен к базе данных.");
    }
}
