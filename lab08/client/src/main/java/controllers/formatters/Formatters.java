package controllers.formatters;

import alertManager.DialogManager;
import exceptions.WrongArgumentException;
import javafx.scene.control.TextFormatter;
import languages.ErrorLocalizator;
import languages.Localizator;

import java.util.function.UnaryOperator;

public class Formatters {
    private Localizator errorLocalizator = ErrorLocalizator.getInstance();
    public TextFormatter<String> getIntTextFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newValue = change.getControlNewText().trim();
            try {
                if (newValue.isBlank()) {
                    return change;
                }
                if (newValue.equals("-") || newValue.equals("0")){
                    throw new WrongArgumentException(errorLocalizator.getStringFormatted("ArgumentValidation", new Object[]{" > 0."}));

                }
                Integer.parseInt(newValue);
                return change;
            }
            catch (WrongArgumentException wrongArgumentException){
                DialogManager.createErrorAlert(wrongArgumentException.getMessage());
                return null;
            }
            catch (NumberFormatException numberFormatException) {
                DialogManager.createErrorAlert(errorLocalizator.getStringFormatted("ArgumentType", new Object[]{" int."}));
                return null;
            }
        };

        return new TextFormatter<>(filter);
    }

    public TextFormatter<String> getXTextFormatter(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newX = change.getControlNewText().trim();
            try {
                if (newX.isBlank() || newX.equals("-")) {
                    return change;
                }
                long x = Long.parseLong(newX);
                if (x <= -28){
                    throw new WrongArgumentException(errorLocalizator.getStringFormatted("ArgumentValidation", new Object[]{" > -28."}));
                }
                return change;
            }
            catch (WrongArgumentException wrongArgumentException){
                DialogManager.createErrorAlert(wrongArgumentException.getMessage());
                return null;
            }
            catch (NumberFormatException numberFormatException) {
                DialogManager.createErrorAlert(errorLocalizator.getStringFormatted("ArgumentType", new Object[]{" long."}));
                return null;
            }
        };
        return new TextFormatter<>(filter);
    }

    public TextFormatter<String> getYTextFormatter(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newY = change.getControlNewText().trim();
            try {
                if (newY.isBlank() || newY.equals("-")) {
                    return change;
                }
                long y = Long.parseLong(newY);
                return change;
            }
            catch (NumberFormatException numberFormatException) {
                DialogManager.createErrorAlert(errorLocalizator.getStringFormatted("ArgumentType", new Object[]{" long."}));
                return null;
            }
        };
        return new TextFormatter<>(filter);
    }

    public TextFormatter<String> getDepthTextFormatter(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newDepth = change.getControlNewText().trim();
            try {
                if (newDepth.isBlank() || newDepth.equals("-") || newDepth.equals(".")) {
                    return change;
                }
                Float.parseFloat(newDepth);
                return change;
            }
            catch (NumberFormatException numberFormatException) {
                DialogManager.createErrorAlert(errorLocalizator.getStringFormatted("ArgumentType", new Object[]{" float."}));
                return null;
            }
        };
        return new TextFormatter<>(filter);
    }

    public TextFormatter<String> getIdTextFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newValue = change.getControlNewText().trim();
            try {
                if (newValue.isBlank()) {
                    return change;
                }
                if (newValue.equals("-") || newValue.equals("0")){
                    throw new WrongArgumentException(errorLocalizator.getStringFormatted("ArgumentValidation", new Object[]{" > 0."}));

                }
                Long.parseLong(newValue);
                return change;
            }
            catch (WrongArgumentException wrongArgumentException){
                DialogManager.createErrorAlert(wrongArgumentException.getMessage());
                return null;
            }
            catch (NumberFormatException numberFormatException) {
                DialogManager.createErrorAlert(errorLocalizator.getStringFormatted("ArgumentType", new Object[]{" long."}));
                return null;
            }
        };

        return new TextFormatter<>(filter);
    }
}
