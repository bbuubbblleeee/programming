package controllers;

import alertManager.DialogManager;
import clientMain.ClientMain;
import collection.Dragon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import languages.ErrorLocalizator;
import languages.InfoLocalizator;
import languages.Localizator;
import languages.UILocalizator;
import javafx.scene.control.Label;
import transfer.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class RegisterController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private ComboBox<String> languageBox;

    @FXML
    private Label registerLabel;

    @FXML
    private Label loginPasswordLabel;



    private Runnable callAuth;
    private Localizator errorLocalizator = ErrorLocalizator.getInstance();
    private Localizator infoLocalizator = InfoLocalizator.getInstance();
    private Localizator uiLocalizator = UILocalizator.getInstance();


    @FXML
    void initialize(){
        setLanguage();

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
            setLanguage();
        });
    }

    @FXML
    void signUp(){
        if (passwordField.getText().isBlank() || loginField.getText().isBlank()){
            DialogManager.createErrorAlert(errorLocalizator.getString("LoginPasswordNull"));
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
                    DialogManager.createInfoAlert(infoLocalizator.getString("RegistrationSuccess"));
                    callAuth.run();
                }
                else{
                    DialogManager.createErrorAlert(errorLocalizator.getString(response));
                }
            }
            catch (IOException ioException){
                System.out.println(ioException.getMessage());
                DialogManager.createErrorAlert(errorLocalizator.getString("RegistrationFailed"));
            }
        }
    }


    private boolean successfulRegistration(String response){
        return response.equals("RegistrationSuccess");
    }

    public void setCallAuth(Runnable callAuth) {
        this.callAuth = callAuth;
    }


    private void setLanguage(){
        loginField.setPromptText(uiLocalizator.getString("Login"));
        passwordField.setPromptText(uiLocalizator.getString("Password"));
        registerButton.textProperty().setValue(uiLocalizator.getString("RegisterButton"));
        registerLabel.textProperty().setValue(uiLocalizator.getString("Registration"));
        loginPasswordLabel.textProperty().setValue(uiLocalizator.getString("LoginPasswordLabel"));
        languageBox.setPromptText(uiLocalizator.getString("LanguageComboBox"));
    }

}
