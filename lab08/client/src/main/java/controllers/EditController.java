package controllers;

import alertManager.DialogManager;
import collection.*;

import collection.Color;
import controllers.formatters.Formatters;
import exceptions.WrongArgumentException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.function.Consumer;


public class EditController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private ComboBox<Color> colorBox;

    @FXML
    private ComboBox<DragonType> typeBox;

    @FXML
    private ComboBox<DragonCharacter> characterBox;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private TextField depthField;

    @FXML
    private TextField treasureField;

    private Consumer<Dragon> getDragon;

    @FXML
    void initialize(){
        colorBox.getItems().addAll(Color.values());
        colorBox.getItems().add(null);
        typeBox.getItems().addAll(DragonType.values());
        characterBox.getItems().addAll(DragonCharacter.values());
        characterBox.getItems().add(null);
        Formatters formatters = new Formatters();


        TextFormatter<String> ageFormatter = formatters.getIntTextFormatter();
        ageField.setTextFormatter(ageFormatter);


        TextFormatter<String> xFormatter = formatters.getXTextFormatter();
        xField.setTextFormatter(xFormatter);

        TextFormatter<String> yFormatter = formatters.getYTextFormatter();
        yField.setTextFormatter(yFormatter);

        TextFormatter<String> depthFormatter = formatters.getDepthTextFormatter();
        depthField.setTextFormatter(depthFormatter);

        TextFormatter<String> treasureFormatter = formatters.getIntTextFormatter();
        treasureField.setTextFormatter(treasureFormatter);


//        ageField.textProperty().addListener((observable, oldValue, newValue) -> {
//            try{
//                //                if (ageStr == null || ageStr.isBlank()){
////                    throw new WrongArgumentException("Аргумент не может быть нулевым.");
////                }
//                Integer age = Integer.parseInt(newValue);
//                if (age <= 0){
//                    throw new WrongArgumentException("Недопустимое значение.\nОжидалось значение > 0.");
//                }
//            }
//            catch (WrongArgumentException wrongArgumentException){
//                ageField.setText(oldValue);
//                AlertManager.createErrorAlert(wrongArgumentException.getMessage());
//            }
//            catch (NumberFormatException numberFormatException){
//                ageField.setText(oldValue);
//                AlertManager.createErrorAlert("Недопустимое значение.\nОжидался аргумент типа int.");
//            }
//        });

//        xField.textProperty().addListener((observable, oldValue, newValue) -> {
//            try{
////                if (xStr == null || xStr.isBlank()){
////                    throw new WrongArgumentException("Аргумент не может быть нулевым.");
////                }
//                long x = Long.parseLong(newValue);
//                if (x <= -28){
//                    throw new WrongArgumentException("Недопустимое значение.\nОжидалось значение > -28.");
//                }
//            }
//            catch (WrongArgumentException wrongArgumentException){
//                xField.setText(oldValue);
//                AlertManager.createErrorAlert(wrongArgumentException.getMessage());
//            }
//            catch (NumberFormatException numberFormatException){
//                xField.setText(oldValue);
//                AlertManager.createErrorAlert("Недопустимое значение.\nОжидался аргумент типа long.");
//            }
//        });

//        yField.textProperty().addListener((observable, oldValue, newValue) -> {
//            try{
////                if (yStr == null || yStr.isBlank()){
////                    throw new WrongArgumentException("Аргумент не может быть нулевым.");
////                }
//                Long.parseLong(newValue);
//            }
//            catch (NumberFormatException numberFormatException){
//                yField.setText(oldValue);
//                AlertManager.createErrorAlert("Недопустимое значение.\nОжидался аргумент типа long.");
//
//            }
//        });

//        depthField.textProperty().addListener((observable, oldValue, newValue) -> {
//            try{
////                if (yStr == null || yStr.isBlank()){
////                    throw new WrongArgumentException("Аргумент не может быть нулевым.");
////                }
//                Float.parseFloat(newValue);
//            }
//            catch (NumberFormatException numberFormatException){
//                depthField.setText(oldValue);
//                AlertManager.createErrorAlert("Недопустимое значение.\nОжидался аргумент типа float.");
//
//            }
//        });

//        treasureField.textProperty().addListener((observable, oldValue, newValue) -> {
//            try{
////                if (yStr == null || yStr.isBlank()){
////                    throw new WrongArgumentException("Аргумент не может быть нулевым.");
////                }
//                int numberOfTreasures = Integer.parseInt(newValue);
//                if (numberOfTreasures <= 0){
//                    throw new WrongArgumentException("Недопустимое значение.\nОжидалось значение > 0.");
//                }
//            }
//            catch (WrongArgumentException wrongArgumentException){
//                treasureField.setText(oldValue);
//                AlertManager.createErrorAlert(wrongArgumentException.getMessage());
//            }
//            catch (NumberFormatException numberFormatException){
//                treasureField.setText(oldValue);
//                AlertManager.createErrorAlert("Недопустимое значение.\nОжидался аргумент типа int.");
//
//            }
//        });
    }



    @FXML
    void send(){
        try{
            Stage stage = (Stage) nameField.getScene().getWindow();
            nullChecker(nameField.getText().trim(), "\"имя\"");
            nullChecker(ageField.getText().trim(), "\"возраст\"");
            nullChecker(xField.getText().trim(), "\"Х\"");
            nullChecker(yField.getText().trim(), "\"У\"");
            nullChecker(depthField.getText().trim(), "\"глубина\"");
            nullChecker(treasureField.getText().trim(), "\"количество сокровищ\"");

            Dragon dragon = getDragon();
            if (getDragon != null){
                getDragon.accept(dragon);
            }
            stage.close();
        }
        catch (WrongArgumentException wrongArgumentException){
            DialogManager.createErrorAlert(wrongArgumentException.getMessage());
        }
    }

    @FXML
    void cancel(){
        if (getDragon != null){
            getDragon.accept(null);
        }
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }


    public Dragon getDragon(){
        String name = nameField.textProperty().get();
        int age = Integer.parseInt(ageField.textProperty().get());
        Color color = colorBox.getSelectionModel().getSelectedItem();
        DragonType type = typeBox.getSelectionModel().getSelectedItem();
        DragonCharacter character = characterBox.getSelectionModel().getSelectedItem();
        Coordinates coordinates = new Coordinates(Long.parseLong(xField.getText()), Long.parseLong(yField.getText()));
        DragonCave cave = new DragonCave(Float.parseFloat(depthField.getText()), Integer.parseInt(treasureField.getText()));
        return new Dragon(name, coordinates, age, color, type, cave, character);
    }


    private void nullChecker(Object object, String field) throws WrongArgumentException {
        if (object == null || object.equals("")) {
            throw new WrongArgumentException("Аргумент " + field + " не может быть нулевым.");
        }
    }

    public void setGetDragon(Consumer<Dragon> getDragon) {
        this.getDragon = getDragon;
    }

    private void clearFields(){
        ageField.clear();
        nameField.clear();
        colorBox.getSelectionModel().clearSelection();
        typeBox.getSelectionModel().clearSelection();
        characterBox.getSelectionModel().clearSelection();
        xField.clear();
        yField.clear();
        depthField.clear();
        treasureField.clear();
    }

    public void setOnClose(){
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setOnCloseRequest(event -> getDragon.accept(null));
    }

}
