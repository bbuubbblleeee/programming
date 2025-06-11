package languages;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class InfoLocalizator extends Localizator{
    private static Localizator errorLocalizator = null;
    private InfoLocalizator (){};
    public static Localizator getInstance(){
        if (errorLocalizator == null){
            errorLocalizator = new InfoLocalizator();
        }
        return errorLocalizator;
    }
    private final Map<String, String> ru = new HashMap<>() {
        {
            put("AuthSuccess", "Пользователь успешно подключен к базе данных.");
            put("Name", "имя");
            put("Age", "возраст");
            put("Character", "характер");
            put("Color", "цвет");
            put("Type", "тип");
            put("CreationDate", "дата инициализации");
            put("Id", "идентификатор");
            put("Depth", "глубина");
            put("Treasure", "количество сокровищ");
            put("TypeAge", "Введите возраст");
            put("TypeId", "Введите id");
            put("TypePath", "Введите путь к скрипту");
            put("ScriptPath", "путь к скрипту");
            put("TableShow", "Коллекция представлена в таблице.");
            put("RegistrationSuccess", "Пользователь успешно зарегистрирован.\nВойдите в аккаунт.");

        }
    };
    private final Map<String, String> is = new HashMap<>() {
        {
            put("AuthSuccess", "Notandinn hefur tengst gagnagrunninum.");
            put("Name", "nafn");
            put("Age", "aldur");
            put("Character", "karakter");
            put("Color", "lit");
            put("Type", "gerð");
            put("CreationDate", "upphafsdagsetningu");
            put("Id", "auðkenni");
            put("Depth", "dýpt");
            put("Treasure", "magn af fjársjóði");
            put("TypeAge", "Sláðu inn aldur þinn");
            put("TypeId", "Sláðu inn auðkenni");
            put("TypePath", "Sláðu inn slóðina að handritinu");
            put("TableShow", "Safnið er sett fram í töflunni.");
            put("RegistrationSuccess", "Notandi hefur verið skráður.\nSkráðu þig inn á reikninginn þinn.");
            put("ScriptPath", "leið að handriti");


        }
    };
    private final Map<String, String> el = new HashMap<>() {
        {
            put("AuthSuccess", "Ο χρήστης έχει συνδεθεί επιτυχώς στη βάση δεδομένων.");
            put("Name", "όνομα");
            put("Age", "ηλικία");
            put("Character", "χαρακτήρας");
            put("Color", "χρώμα");
            put("Type", "τύπος");
            put("CreationDate", "ημερομηνία αρχικοποίησης");
            put("Id", "αναγνωριστικό");
            put("Depth", "βάθος");
            put("Treasure", "ποσό του θησαυρού");
            put("TypeAge", "Εισάγετε την ηλικία");
            put("TypeId", "Εισάγετε id");
            put("TypePath", "Εισάγετε τη διαδρομή του σεναρίου");
            put("TableShow", "Η συλλογή συνοψίζεται στον πίνακα.");
            put("RegistrationSuccess", "Ο χρήστης έχει εγγραφεί επιτυχώς.\nΣυνδεθείτε στο λογαριασμό σας.");
            put("ScriptPath", "διαδρομή προς το σενάριο");


        }
    };
    private final Map<String, String> es_PR = new HashMap<>() {
        {
            put("AuthSuccess", "El usuario se ha conectado correctamente a la base de datos.");
            put("Name", "nombre");
            put("Age", "edad");
            put("Character", "carácter");
            put("Color", "color");
            put("Type", "tipo");
            put("CreationDate", "fecha de inicialización");
            put("Id", "identificador");
            put("Depth", "profundidad");
            put("Treasure", "cantidad de tesoro");
            put("TypeAge", "Introduzca la edad");
            put("TypeId", "Introduzca un identificador");
            put("TypePath", "Introduzca la ruta del script");
            put("TableShow", "La recopilación se resume en el cuadro.");
            put("RegistrationSuccess", "El usuario se ha registrado correctamente.\nAcceda a su cuenta.");
            put("ScriptPath", "ruta al script");


        }
    };

    @Override
    public String getString(String key) {
        switch (locale.getLanguage()) {
            case "ru" -> {
                return ru.getOrDefault(key, "info");
            }
            case "is" -> {
                return is.getOrDefault(key, "info");
            }
            case "el" -> {
                return el.getOrDefault(key, "info");
            }
            case "es" -> {
                return es_PR.getOrDefault(key, "info");
            }
            default -> {
                return "Info";
            }
        }
    }

    @Override
    public String getStringFormatted(String key, String field){
        switch (locale.getLanguage()) {
            case "ru" -> {
                String pattern = ru.get(key);
                return MessageFormat.format(pattern, field);
            }
            case "is" -> {
                String pattern = is.get(key);
                return MessageFormat.format(pattern, field);
            }
            case "el" -> {
                String pattern = el.get(key);
                return MessageFormat.format(pattern, field);
            }
            case "es" -> {
                String pattern = es_PR.get(key);
                return MessageFormat.format(pattern, field);
            }
            default -> {
                return "Info";
            }
        }
    }
}
