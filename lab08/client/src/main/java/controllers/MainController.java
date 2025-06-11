package controllers;

import alertManager.DialogManager;
import client.ReadData;
import clientMain.ClientMain;
import clientMain.Execute_script;
import clientMain.Handler;
import collection.Color;
import collection.Dragon;
import collection.DragonCharacter;
import collection.DragonType;
import controllers.formatters.Formatters;
import exceptions.CancelledAction;
import exceptions.DbErrorException;
import exceptions.InvalidFileException;
import exceptions.WrongArgumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.DirectionalLight;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import languages.ErrorLocalizator;
import languages.InfoLocalizator;
import languages.Localizator;
import languages.UILocalizator;
import transfer.Request;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainController {
    @FXML
    private Menu userLogin;

    @FXML
    private Menu languageMenu;

    @FXML
    private Tab collectionTab;

    @FXML
    private Tab visualisationTab;

    @FXML
    private MenuItem russianMenuItem;

    @FXML
    private MenuItem icelandicMenuItem;

    @FXML
    private MenuItem greekMenuItem;

    @FXML
    private MenuItem spanishMenuItem;

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

    @FXML
    private Label commandsAvailable;

    private ObservableList<Dragon> dragons = FXCollections.observableArrayList();
    private Runnable callEdit;
    private Dragon dragon;
    private final Consumer<Dragon> getDragon = dragon -> this.dragon = dragon;
    private Localizator errorLocalizator = ErrorLocalizator.getInstance();
    private Localizator infoLocalizator = InfoLocalizator.getInstance();
    private Localizator uiLocalizator = UILocalizator.getInstance();


    @FXML
    void initialize(){
        setLanguage();

        russianMenuItem.setOnAction(event ->
                Localizator.setLocale(Locale.forLanguageTag("ru"))
        );

        icelandicMenuItem.setOnAction(event ->
                Localizator.setLocale(Locale.forLanguageTag("is"))
        );

        greekMenuItem.setOnAction(event ->
                Localizator.setLocale(Locale.forLanguageTag("el"))
        );

        spanishMenuItem.setOnAction(event ->
                Localizator.setLocale(Locale.forLanguageTag("es-PR"))
        );

        Localizator.localeProperty.addListener((observable, oldValue, newValue) -> {
            setLanguage();
        });

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
            String age = DialogManager.createTextInputDialog(infoLocalizator.getString("TypeAge"), infoLocalizator.getString("Age"), new Formatters().getIntTextFormatter());

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
        DialogManager.createInfoAlert(infoLocalizator.getString("TableShow"));
    }

    @FXML
    void update(){
        try {
            String id = DialogManager.createTextInputDialog(infoLocalizator.getString("TypeId"), infoLocalizator.getString("Id"), new Formatters().getIdTextFormatter());
            if (id.isBlank()){
                throw new CancelledAction(errorLocalizator.getString("CancelledAction"));
            }
            if (dragons.stream().filter(dragon -> dragon.getId().toString().equals(id)).findAny().isEmpty()){
                throw new WrongArgumentException(errorLocalizator.getString("IdNotFound"));
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
            String age = DialogManager.createTextInputDialog(infoLocalizator.getString("TypeAge"), infoLocalizator.getString("Age"), new Formatters().getIntTextFormatter());
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
            String id = DialogManager.createTextInputDialog(infoLocalizator.getString("TypeId"), infoLocalizator.getString("Id"), new Formatters().getIntTextFormatter());
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

    @FXML
    void executeScript(){
        Execute_script executeScript = new Execute_script();
        try {
            String path = DialogManager.createTextInputDialog(infoLocalizator.getString("TypePath"), infoLocalizator.getString("ScriptPath"), null);
            Handler.setStack(path);
            executeScript.execute(path);
            DialogManager.createInfoScrolledAlert(executeScript.getResult());
        }
        catch (Exception exception){
            DialogManager.createInfoScrolledAlert(executeScript.getResult() + "\n" + exception.getMessage());
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
            DialogManager.createErrorAlert(errorLocalizator.getString("NoAnswer"));
            throw new DbErrorException(errorLocalizator.getString("DbDataGet"));
        }
    }



    public void setCallEdit(Runnable callEdit) {
        this.callEdit = callEdit;
    }



    public Consumer<Dragon> getGetDragon() {
        return getDragon;
    }

    private void setLanguage(){
        languageMenu.setText(uiLocalizator.getString("LanguageComboBox"));
        collectionTab.setText(uiLocalizator.getString("CollectionTab"));
        visualisationTab.setText(uiLocalizator.getString("VisualisationTab"));
        idColumn.setText(uiLocalizator.getString("IdLabel"));
        nameColumn.setText(uiLocalizator.getString("NameLabel"));
        ageColumn.setText(uiLocalizator.getString("AgeLabel"));
        xColumn.setText(uiLocalizator.getString("XColumn"));
        yColumn.setText(uiLocalizator.getString("YColumn"));
        dateColumn.setText(uiLocalizator.getString("CreationDateLabel"));
        colorColumn.setText(uiLocalizator.getString("ColorLabel"));
        typeColumn.setText(uiLocalizator.getString("TypeLabel"));
        characterColumn.setText(uiLocalizator.getString("CharacterLabel"));
        depthColumn.setText(uiLocalizator.getString("DepthLabel"));
        treasureColumn.setText(uiLocalizator.getString("TreasureLabel"));
        ownerColumn.setText(uiLocalizator.getString("Owner"));
        commandsAvailable.textProperty().setValue(uiLocalizator.getString("CommandsAvailable"));







    }
}

