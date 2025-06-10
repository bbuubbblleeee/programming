package controllers;

import alertManager.DialogManager;
import clientMain.ClientMain;
import collection.Color;
import collection.Dragon;
import collection.DragonCharacter;
import collection.DragonType;
import controllers.formatters.Formatters;
import exceptions.CancelledAction;
import exceptions.DbErrorException;
import exceptions.WrongArgumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import transfer.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainController {
    @FXML
    private Menu userLogin;

    @FXML
    private TableView<Dragon> collectionTable;

    @FXML
    private TableColumn<Dragon, Long> idColumn;

    @FXML
    private TableColumn<Dragon, String> nameColumn;

    @FXML
    private TableColumn<Dragon, Integer> ageColumn;

    @FXML
    private TableColumn<Dragon, Long> xColumn;

    @FXML
    private TableColumn<Dragon, Long> yColumn;

    @FXML
    private TableColumn<Dragon, String> dateColumn;

    @FXML
    private TableColumn<Dragon, Color> colorColumn;

    @FXML
    private TableColumn<Dragon, DragonType> typeColumn;

    @FXML
    private TableColumn<Dragon, DragonCharacter> characterColumn;

    @FXML
    private TableColumn<Dragon, Float> depthColumn;

    @FXML
    private TableColumn<Dragon, Integer> treasureColumn;

    @FXML
    private TableColumn<Dragon, String> ownerColumn;

    private ObservableList<Dragon> dragons = FXCollections.observableArrayList();
    private Runnable callEdit;
    private Dragon dragon;
    private final Consumer<Dragon> getDragon = dragon -> this.dragon = dragon;


    @FXML
    void initialize(){
        userLogin.setText(ClientMain.getLogin());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        xColumn.setCellValueFactory(new PropertyValueFactory<>("coordinateX"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("coordinateY"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        List<Color> sortedColor = Arrays.stream(Color.values())
                                    .sorted(Comparator.comparing(Enum::name))
                                    .toList();
        colorColumn.setComparator(Comparator.comparing(sortedColor::indexOf));

        characterColumn.setCellValueFactory(new PropertyValueFactory<>("character"));
        List<DragonCharacter> sortedCharacter = Arrays.stream(DragonCharacter.values())
                                                .sorted(Comparator.comparing(Enum::name))
                                                .toList();
        characterColumn.setComparator(Comparator.comparing(sortedCharacter::indexOf));

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        List<DragonType> sortedType = Arrays.stream(DragonType.values())
                                            .sorted(Comparator.comparing(Enum::name))
                                            .toList();
        typeColumn.setComparator(Comparator.comparing(sortedType::indexOf));

        depthColumn.setCellValueFactory(new PropertyValueFactory<>("depthCave"));
        treasureColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfTreasures"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        collectionTable.setItems(dragons);
        getDragons();
    }

    @FXML
    void add(){
        try {
            callEdit.run();
            String response = ClientMain.sendAndGetResponse(new Request("add", new String[0], new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
            getDragons();
        }
        catch (IOException ioException){

        }
    }

    @FXML
    void addIfMax(){
        try {
            callEdit.run();
            String response = ClientMain.sendAndGetResponse(new Request("add_if_max", new String[0], new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
            getDragons();
        }
        catch (IOException ioException){

        }
    }

    @FXML
    void clear(){
        try {
            String response = ClientMain.sendAndGetResponse(new Request("clear", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
            getDragons();
        }
        catch (IOException ioException){

        }
    }

    @FXML
    void countByAge(){
        String response = null;
        try {
            String age = DialogManager.createTextInputDialog("age", new Formatters().getIntTextFormatter());

            String[] args = new String[1];
            args[0] = age;
            response = ClientMain.sendAndGetResponse(new Request("count_by_age", args, new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
        }
        catch (CancelledAction ignored){
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(response);
        }
    }

    @FXML
    void exit(){
        Stage stage = (Stage) collectionTable.getScene().getWindow();
        System.out.println(stage);
        stage.close();
    }

    @FXML
    void help(){
        try {
            String response = ClientMain.sendAndGetResponse(new Request("help", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoScrolledAlert(response);
        }
        catch (Exception exception){

        }
    }

    @FXML
    void info(){
        try {
            String response = ClientMain.sendAndGetResponse(new Request("info", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
        }
        catch (Exception exception){

        }
    }

    @FXML
    void show(){
        DialogManager.createInfoAlert("Коллекция представлена в таблице.");
    }

    @FXML
    void update(){
        try {
            String id = DialogManager.createTextInputDialog("id", new Formatters().getIdTextFormatter());
            if (id.isBlank()){
                throw new CancelledAction("Действие было отменено.");
            }
            if (dragons.stream().filter(dragon -> dragon.getId().toString().equals(id)).findAny().isEmpty()){
                throw new WrongArgumentException("Объект с таким id не был найден.");
            }
            String[] args = new String[1];
            args[0] = id;
            callEdit.run();
            String response = ClientMain.sendAndGetResponse(new Request("update", args, new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
            getDragons();
        }
        catch (WrongArgumentException wrongArgumentException){
            DialogManager.createErrorAlert(wrongArgumentException.getMessage());
        }
        catch(Exception ignored){}
    }

    @FXML
    void printFieldDescendingCharacter(){
        try{
            String response = ClientMain.sendAndGetResponse(new Request("print_field_descending_character", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoScrolledAlert(response);
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(ioException.getMessage());
        }
    }

    @FXML
    void removeAnyByAge(){
        try{
            String age = DialogManager.createTextInputDialog("age", new Formatters().getIntTextFormatter());
            String[] args = new String[1];
            args[0] = age;
            String response = ClientMain.sendAndGetResponse(new Request("remove_any_by_age", args, new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
            getDragons();
        }
        catch (CancelledAction ignored){
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(ioException.getMessage());
        }
    }

    @FXML
    void removeById(){
        try{
            String id = DialogManager.createTextInputDialog("id", new Formatters().getIntTextFormatter());
            String[] args = new String[1];
            args[0] = id;
            String response = ClientMain.sendAndGetResponse(new Request("remove_by_id", args, new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
            getDragons();
        }
        catch (CancelledAction ignored){
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(ioException.getMessage());
        }
    }

    @FXML
    void removeGreater(){
        try{
            callEdit.run();
            String response = ClientMain.sendAndGetResponse(new Request("remove_greater", new String[0], new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
            getDragons();
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(ioException.getMessage());
        }
    }

    @FXML
    void removeLower(){
        try{
            callEdit.run();
            String response = ClientMain.sendAndGetResponse(new Request("remove_lower", new String[0], new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(response);
            getDragons();
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(ioException.getMessage());
        }
    }









    public void getDragons(){
        try {
            String response = ClientMain.sendAndGetResponse(new Request("show", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            Matcher matcher = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL).matcher(response);
            dragons.clear();
            while (matcher.find()) {
                dragons.add(Dragon.fromString(matcher.group(1).trim()));
            }
        }
        catch (Exception exception){
            DialogManager.createErrorAlert("Ошибка: нет ответа от сервера.");
            throw new DbErrorException("Ошибка получения данных из базы данных.");
        }
    }



    public void setCallEdit(Runnable callEdit) {
        this.callEdit = callEdit;
    }



    public Consumer<Dragon> getGetDragon() {
        return getDragon;
    }
}
