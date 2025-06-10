package alertManager;

import exceptions.CancelledAction;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class DialogManager {
    public static void createErrorAlert(String error){
        DialogManager.createAlert(Alert.AlertType.ERROR, error);
    }
    public static void createInfoAlert(String info){
        DialogManager.createAlert(Alert.AlertType.INFORMATION, info);
    }
    public static void createInfoScrolledAlert(String info){
        DialogManager.createScrolledAlert(Alert.AlertType.INFORMATION, info);
    }

    public static String createTextInputDialog(String field, TextFormatter<String> textFormatter){
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Введите " + field);
        textInputDialog.setContentText(field);

        TextField textField = textInputDialog.getEditor();
        if (textFormatter != null) {
            textField.setTextFormatter(textFormatter);
        }
        Button okButton = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.OK);
        Button cancelButton = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL);

        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (textField.getText() == null || textField.getText().isBlank()){
                createErrorAlert("Аргумент не может быть нулевым.");
                event.consume();
            }
        });
        AtomicBoolean cancelled = new AtomicBoolean(false);
        cancelButton.addEventFilter(ActionEvent.ACTION, event -> {
            cancelled.set(true);
        });
        Stage stage = (Stage) textInputDialog.getDialogPane().getScene().getWindow();
        stage.setOnCloseRequest(event -> cancelled.set(true));


        Optional<String> result = textInputDialog.showAndWait();
        if (cancelled.get()){
            throw new CancelledAction("Действие было отменено.");
        }
        return result.orElse(null);
    }


    private static void createAlert(Alert.AlertType type, String title){
        Alert alert = new Alert(type);
        alert.setContentText(title);
        alert.setTitle(null);
        alert.showAndWait();
    }

    private static void createScrolledAlert(Alert.AlertType type, String title){
        Alert alert = new Alert(type);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(new TextArea(title));
        alert.getDialogPane().setContent(scrollPane);
        alert.setTitle(null);
        alert.showAndWait();
    }
}
