package languages;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class UILocalizator extends Localizator{
    private static Localizator uiLocalizator = null;
    private UILocalizator (){};
    public static Localizator getInstance(){
        if (uiLocalizator == null){
            uiLocalizator = new UILocalizator();
        }
        return uiLocalizator;
    }


    private Map<String, String> ru = new HashMap<>(){{
        put("Login", "Логин");
        put("Password", "Пароль");
        put("Auth", "Войти");
        put("Registration", "Регистрация");
        put("AuthLabel","Вход в аккаунт");
        put("LoginPasswordLabel","Введите логин и пароль");
        put("OrLabel","или");
        put("CreateNewLabel","создайте новый аккаунт");
        put("RegisterButton", "Зарегистрироваться");
        put("LanguageComboBox", "Язык");
        put("NameLabel", "Имя");
        put("AgeLabel", "Возраст");
        put("CharacterLabel", "Характер");
        put("ColorLabel", "Цвет");
        put("TypeLabel", "Тип");
        put("CreationDateLabel", "Дата инициализации");
        put("IdLabel", "Идентификатор");
        put("DepthLabel", "Глубина");
        put("TreasureLabel", "Количество сокровищ");
        put("TitleLabel", "Ввод данных об объекте");
        put("CoordinatesLabel", "Координаты");
        put("CaveLabel", "Пещера");
        put("SendButton", "Отправить");
        put("CancelButton", "Отменить");
        put("CollectionTab", "Коллекция");
        put("VisualisationTab", "Визуализация");
        put("XColumn", "Координата X");
        put("YColumn", "Координата Y");
        put("Owner", "Владелец");
        put("CommandsAvailable", "Доступные команды:");




    }};

    private Map<String, String> is = new HashMap<>(){{
        put("Login", "Innskráning");
        put("Password", "Lykilorð");
        put("Auth", "Innskráning");
        put("Registration", "Skráning");
        put("AuthLabel","Skráðu þig inn á reikning");
        put("LoginPasswordLabel","Sláðu inn notandanafn og lykilorð");
        put("OrLabel","eða");
        put("CreateNewLabel","búa til nýjan reikning");
        put("RegisterButton", "Skráðu þig");
        put("LanguageComboBox", "Tungumál");
        put("NameLabel", "Nafn");
        put("AgeLabel", "Aldur");
        put("CharacterLabel", "Karakter");
        put("ColorLabel", "Lit");
        put("TypeLabel", "Gerð");
        put("CreationDateLabel", "Upphafsdagsetningu");
        put("IdLabel", "Auðkenni");
        put("DepthLabel", "Dýpt");
        put("TreasureLabel", "Magn af fjársjóði");
        put("TitleLabel", "Að slá inn gögn um hlut");
        put("CoordinatesLabel", "Hnit");
        put("CaveLabel", "Hellir");
        put("SendButton", "Senda");
        put("CancelButton", "Hætta við");
        put("CollectionTab", "Safn");
        put("VisualisationTab", "Visualization");
        put("XColumn", "X hnit");
        put("YColumn", "Y hnit");
        put("Owner", "Eigandi");
        put("CommandsAvailable", "Tiltækar skipanir:");




    }};

    private Map<String, String> el = new HashMap<>(){{
        put("Login", "Σύνδεση");
        put("Password", "Κωδικός πρόσβασης");
        put("Auth", "Συνδεθείτε");
        put("Registration", "Εγγραφή");
        put("AuthLabel","Σύνδεση λογαριασμού");
        put("LoginPasswordLabel","Εισάγετε τη σύνδεσή σας και τον κωδικό πρόσβασής σας");
        put("OrLabel","ή");
        put("CreateNewLabel","δημιουργία νέου λογαριασμού");
        put("RegisterButton", "Εγγραφείτε");
        put("LanguageComboBox", "Γλώσσα");
        put("NameLabel", "Ονομα");
        put("AgeLabel", "Ηλικία");
        put("CharacterLabel", "Χαρακτήρας");
        put("ColorLabel", "Χρώμα");
        put("TypeLabel", "Τύπος");
        put("CreationDateLabel", "Hμερομηνία αρχικοποίησης");
        put("IdLabel", "Aναγνωριστικό");
        put("DepthLabel", "Βάθος");
        put("TreasureLabel", "Αριθμός θησαυρών");
        put("TitleLabel", "Εισαγωγή δεδομένων αντικειμένου");
        put("CoordinatesLabel", "Συντεταγμένες");
        put("CaveLabel", "Σπήλαιο");
        put("SendButton", "Στέλνω");
        put("CancelButton", "Ματαίωση");
        put("CollectionTab", "Συλλογή");
        put("VisualisationTab", "Οραματισμός");
        put("XColumn", "Χ συντεταγμένη");
        put("YColumn", "Y συντεταγμένη");
        put("Owner", "Ιδιοκτήτης");
        put("CommandsAvailable", "Διαθέσιμες εντολές:");







    }};

    private Map<String, String> es_PR = new HashMap<>(){{
        put("Login", "Nombre de usuario");
        put("Password", "Contraseña");
        put("Auth", "Iniciar sesión");
        put("Registration", "Inscripción");
        put("AuthLabel","Acceso a la cuenta");
        put("LoginPasswordLabel","Introduzca su nombre de usuario y contraseña");
        put("OrLabel","o");
        put("CreateNewLabel","crear una nueva cuenta");
        put("RegisterButton", "Inscríbete");
        put("LanguageComboBox", "Idioma");
        put("NameLabel", "Nombre");
        put("AgeLabel", "Edad");
        put("CharacterLabel", "Carácter");
        put("ColorLabel", "Color");
        put("TypeLabel", "Tipo");
        put("CreationDateLabel", "Fecha de inicialización");
        put("IdLabel", "Identificador");
        put("DepthLabel", "Profundidad");
        put("TreasureLabel", "Cantidad de tesoro");
        put("TitleLabel", "Introducción de datos de objetos");
        put("CoordinatesLabel", "Coordenadas");
        put("CaveLabel", "Cueva");
        put("SendButton", "Enviar");
        put("CancelButton", "Cancelar");
        put("CollectionTab", "Recopilación");
        put("VisualisationTab", "Visualización");
        put("XColumn", "Coordenada X");
        put("YColumn", "Coordenada Y");
        put("Owner", "Dueño");
        put("CommandsAvailable", "Comandos disponibles:");






    }};

    @Override
    public String getString(String key) {
        switch (locale.getLanguage()) {
            case "ru" -> {
                return ru.getOrDefault(key, "ui");
            }
            case "is" -> {
                return is.getOrDefault(key, "ui");
            }
            case "el" -> {
                return el.getOrDefault(key, "ui");
            }
            case "es" -> {
                return es_PR.getOrDefault(key, "ui");
            }
            default -> {
                return locale.getLanguage();
            }
        }
    }

    @Override
    public String getStringFormatted(String key, Object[] field) {
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
                return key;
            }
        }
    }

}
