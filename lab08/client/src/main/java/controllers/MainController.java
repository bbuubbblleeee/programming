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
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.DirectionalLight;
import javafx.scene.canvas.Canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import languages.ErrorLocalizator;
import languages.InfoLocalizator;
import languages.Localizator;
import languages.UILocalizator;
import transfer.Request;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
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
    private ScrollPane visualisationPane;

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
    private Map<String, javafx.scene.paint.Color> colorMap = new HashMap<>();
    private Pane pane = new Pane();
    private ArrayList<Dragon> visualisationDragons = new ArrayList<>();


    @FXML
    void initialize(){
        setLanguage();
        pane.setPrefWidth(8192);
        pane.setPrefHeight(8192);
        visualisationPane.setContent(pane);

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
        depthColumn.setComparator(Float::compare);
        treasureColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfTreasures"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        collectionTable.setItems(dragons);
    }

    @FXML
    void add(){
        try {
            callEdit.run();
            if (dragon == null){
                throw new CancelledAction(errorLocalizator.getString("CancelledAction"));
            }
            String response = ClientMain.sendAndGetResponse(new Request("add", new String[0], new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
            getDragons();
        }
        catch (CancelledAction ignored){

        }
        catch (Exception exception){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void addIfMax(){
        try {
            callEdit.run();
            if (dragon == null){
                throw new CancelledAction(errorLocalizator.getString("CancelledAction"));
            }
            String response = ClientMain.sendAndGetResponse(new Request("add_if_max", new String[0], new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
            getDragons();
        }
        catch (CancelledAction ignored){

        }
        catch (Exception exception){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void clear(){
        try {
            String response = ClientMain.sendAndGetResponse(new Request("clear", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
            getDragons();
        }
        catch (Exception exception){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
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
            DialogManager.createInfoAlert(getStringResponse(response));
        }
        catch (CancelledAction ignored){
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void exit(){
        Stage stage = (Stage) collectionTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    void help(){
        try {
            String response = ClientMain.sendAndGetResponse(new Request("help", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoScrolledAlert(infoLocalizator.getString("HelpCommand"));
        }
        catch (Exception exception){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void info(){
        try {
            String response = ClientMain.sendAndGetResponse(new Request("info", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
        }
        catch (Exception exception){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));

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
            if (dragon == null){
                throw new CancelledAction(errorLocalizator.getString("CancelledAction"));
            }
            String response = ClientMain.sendAndGetResponse(new Request("update", args, new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
            getDragons();
        }
        catch (WrongArgumentException wrongArgumentException){
            DialogManager.createErrorAlert(wrongArgumentException.getMessage());
        }
        catch (CancelledAction ignored){
        }
        catch(Exception exception){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void printFieldDescendingCharacter(){
        try{
            String response = ClientMain.sendAndGetResponse(new Request("print_field_descending_character", new String[0], new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            if (!(response.contains("WISE") || response.contains("CUNNING") || response.contains("CHAOTIC"))){
                response = getStringResponse(response);
            }
            DialogManager.createInfoScrolledAlert(response);
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void removeAnyByAge(){
        try{
            String age = DialogManager.createTextInputDialog(infoLocalizator.getString("TypeAge"), infoLocalizator.getString("Age"), new Formatters().getIntTextFormatter());
            String[] args = new String[1];
            args[0] = age;
            String response = ClientMain.sendAndGetResponse(new Request("remove_any_by_age", args, new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
            getDragons();
        }
        catch (CancelledAction ignored){
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void removeById(){
        try{
            String id = DialogManager.createTextInputDialog(infoLocalizator.getString("TypeId"), infoLocalizator.getString("Id"), new Formatters().getIntTextFormatter());
            String[] args = new String[1];
            args[0] = id;
            String response = ClientMain.sendAndGetResponse(new Request("remove_by_id", args, new ArrayList<>(), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
            getDragons();
        }
        catch (CancelledAction ignored){
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void removeGreater(){
        try{
            callEdit.run();
            String response = ClientMain.sendAndGetResponse(new Request("remove_greater", new String[0], new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
            getDragons();
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
        }
    }

    @FXML
    void removeLower(){
        try{
            callEdit.run();
            String response = ClientMain.sendAndGetResponse(new Request("remove_lower", new String[0], new ArrayList<>(List.of(dragon)), ClientMain.getLogin(), ClientMain.getPassword()));
            DialogManager.createInfoAlert(getStringResponse(response));
            getDragons();
        }
        catch (IOException ioException){
            DialogManager.createErrorAlert(errorLocalizator.getString("ServerUnavailable"));
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
            System.out.println(response);
            Matcher matcher = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL).matcher(response);
            dragons.clear();
            while (matcher.find()) {
                Dragon newDragon = Dragon.fromString(matcher.group(1).trim());
                String date = newDragon.getCreationDate();
                newDragon.setCreationDate(changeDateLanguage(date));

                dragons.add(newDragon);
            }
            visualise();
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


    private void visualise(){
        pane.getChildren().clear();
        for (Dragon dragon : dragons){
            Circle circle;
            if (!visualisationDragons.contains(dragon)){
                circle = createCircle(dragon);
                FadeTransition fadeTransition = new FadeTransition(new Duration(1000), circle);
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);
                fadeTransition.play();
                pane.getChildren().add(circle);
            }
            else{
                circle = createCircle(dragon);
                pane.getChildren().add(circle);
            }
            circle.setOnMouseClicked(event -> {
                DialogManager.createInfoAlert(infoLocalizator.getStringFormatted("DragonToString", new Object[]{dragon.getId(), dragon.getName(),
                        dragon.getCoordinateX(), dragon.getCoordinateY(), dragon.getCreationDate(), dragon.getAge(), dragon.getColor(), dragon.getType(),
                        dragon.getCharacter(), dragon.getDepthCave(), dragon.getNumberOfTreasures(), dragon.getOwner()}));
            });
        }
        visualisationDragons.clear();
        visualisationDragons.addAll(dragons);

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
        getDragons();

    }

    public String getStringResponse(String response){
        String[] args = response.split("\\|");
        if (args.length > 1){
            if (args[0].equals("InfoCommand")){
                args[1] = changeDateLanguage(args[1]);
            }
            Object[] arguments = Arrays.copyOfRange(args, 1, args.length);
            String error = errorLocalizator.getStringFormatted(args[0], arguments);
            if (!error.equals("error")){
                return error;
            }
            String info = infoLocalizator.getStringFormatted(args[0], arguments);
            if (!info.equals("info")){
                return info;
            }
        }

        String error = errorLocalizator.getString(args[0]);
        if (!error.equals("error")){
            return error;
        }
        return infoLocalizator.getString(args[0]);
    }

    private String changeDateLanguage(String date){
        date = date.replaceAll("[\\u202F\\u00A0\\u200E]", " ");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy 'г.,' HH:mm:ss", Locale.forLanguageTag("ru"));
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss", Locale.forLanguageTag(Localizator.localeProperty.getValue().getLanguage()));
        return localDateTime.format(dateTimeFormatter1);
    }

    private Circle createCircle(Dragon dragon) {
        String owner = dragon.getOwner();
        if (!colorMap.containsKey(owner)) {
            javafx.scene.paint.Color color = javafx.scene.paint.Color.hsb((owner.hashCode() + 360) % 360, 0.7F, 0.9F);
            colorMap.put(owner, color);
        }
        double size = Math.min(200, Math.max(50, dragon.getAge() * 2));

        double x, y;
        x = (dragon.getCoordinateX() + Math.random()) % 8192;
        y = (dragon.getCoordinateY() + Math.random()) % 8192;
        if (dragon.getCoordinateX() + size > 8192) {
            x = 8192 - size;
        }
        if (dragon.getCoordinateX() - size < 0) {
            x = size;
        }
        if (dragon.getCoordinateY() + size > 8192) {
            y = 8192 - size;
        }
        if (dragon.getCoordinateY() - size < 0) {
            y = size;
        }


        Circle circle = new Circle(x, y, size, colorMap.get(owner));
        circle.setStroke(javafx.scene.paint.Color.BLACK);
        circle.setStrokeWidth(2);
        return circle;
    }
}

