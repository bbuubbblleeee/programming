package controllers;


import alertManager.DialogManager;
import clientMain.ClientMain;
import collection.Dragon;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import languages.ErrorLocalizator;
import languages.Localizator;
import languages.UILocalizator;
import transfer.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class AuthController {
    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox<String> languageBox;

    @FXML
    private Button authButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label authLabel;

    @FXML
    private Label loginPasswordLabel;

    @FXML
    private Label orLabel;

    @FXML
    private Label createNewLabel;


    private Runnable callMain;
    private Runnable callRegister;
    private Localizator errorLocalizator = ErrorLocalizator.getInstance();
    private Localizator uiLocalizator = UILocalizator.getInstance();



    @FXML
    void initialize(){
        languageBox.getItems().addAll("Русский", "Íslenska english", "Ελληνική", "Español (Puerto Rico)");
        languageBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Русский" -> Localizator.setLocale(Locale.forLanguageTag("ru"));
                case "Íslenska english" -> Localizator.setLocale(Locale.forLanguageTag("is"));
                case "Ελληνική" -> Localizator.setLocale(Locale.forLanguageTag("el"));
                case "Español (Puerto Rico)" -> Localizator.setLocale(Locale.forLanguageTag("es-PR"));
            }
        });
        Localizator.localeProperty.addListener((observable, oldValue, newValue) -> {
            loginField.setPromptText(uiLocalizator.getString("Login"));
            passwordField.setPromptText(uiLocalizator.getString("Password"));
            authButton.textProperty().setValue(uiLocalizator.getString("Auth"));
            registerButton.textProperty().setValue(uiLocalizator.getString("Registration"));
            authLabel.textProperty().setValue(uiLocalizator.getString("AuthLabel"));
            loginPasswordLabel.textProperty().setValue(uiLocalizator.getString("LoginPasswordLabel"));
            orLabel.textProperty().setValue(uiLocalizator.getString("OrLabel"));
            createNewLabel.textProperty().setValue(uiLocalizator.getString("CreateNewLabel"));
            languageBox.setPromptText(uiLocalizator.getString("LanguageComboBox"));

        });

    }

    @FXML
    void auth(){
        if (passwordField.getText().isBlank() || loginField.getText().isBlank()){
            DialogManager.createErrorAlert(errorLocalizator.getString("LoginPasswordNull"));
        }
        else {
            //отправка логина и пароля на сервер и проверка
            try {
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
                DialogManager.createInfoAlert(ioException.getMessage());
                //TODO добавить alert (?)
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
//        return response.equals("Пользователь успешно подключен к базе данных.");

        return response.equals("AuthSuccess");
            //TODO на стороне сервера сделать так, чтобы выдавал AuthSuccess и раскомментировать строку выше
    }


}
