package languages;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


public class ErrorLocalizator extends Localizator{
    private static Localizator errorLocalizator = null;
    private ErrorLocalizator (){};
    public static Localizator getInstance(){
        if (errorLocalizator == null){
            errorLocalizator = new ErrorLocalizator();
        }
        return errorLocalizator;
    }
    private final Map<String, String> ru = new HashMap<>() {
        {
            put("CancelledAction", "Действие было отменено.");
            put("LoginPasswordNull", "Логин и пароль не могут быть пустыми.");
            put("Serialize", "Ошибка сериализации.");
            put("ServerUnavailable", "Сервер временно недоступен.");
            put("NoAnswer", "Нет ответа от сервера.");
            put("FileNotFound", "Файл не найден.");
            put("Script", "Ошибка скрипта.");
            put("ScriptPath", "Не указан путь к скрипту.");
            put("ScriptRecursion", "Обнаружена рекурсия в скрипте.");
            put("ArgumentNull", "Аргумент {0} не может быть нулевым.");
            put("IdNotFound", "Объект с таким идентификатором не был найден.");
            put("DbDataGet", "Ошибка получения данных из базы данных.");
            put("ArgumentValidation", "Недопустимое значение.\nОжидалось значение {0}.");
            put("ArgumentType", "Недопустимое значение.\nОжидался аргумент типа {0}.");
            put("ReturnCalled", "Возврат вызван.");
            put("ArgumentBad", "Недопустимое значение.");
            put("ScriptBad", "Неверный скрипт.");
            put("EndOfInput", "Конец ввода.");
            put("EndOfFile", "Конец файла.");
            put("RowsNotFound","Отсутствуют строки, подходящие под условие.");
            put("AddFailed","Дракон не был добавлен в коллекцию, так как его значение меньше значения наибольшего элемента коллекции.");
            put("PasswordFailed","Неверный пароль, попробуйте выполнить вход повторно.");
            put("LoginFailed","Пользователя с таким логином не существует.\nЗарегистрируйтесь.");
            put("AuthFailed","Неуспешная проверка данных пользователя.");
            put("LoginAlreadyExists","Пользователь с таким логином уже существует.");
            put("RegistrationFailed","Неуспешная регистрация пользователя.");
            put("DbDataRemove","Ошибка удаления дракона из базы данных.");
            put("UserHaveNoObjects","В коллекции отсутствуют объекты, принадлежащие данному пользователю. Коллекция не была очищена.");
            put("DbClear","Ошибка очистки базы данных.");
            put("DbWrite", "Ошибка записи данных в базу данных.");
            put("RejectModificate", "Объект не принадлежит данному пользователю, его модификация запрещена.");
            put("NotEnoughRights","Недостаточно прав к файлу.");
            put("DbConnection","Не удалось подключиться к базе данных.");
            put("AuthNeeded","Выполнение команд недоступно без авторизации. Пожалуйста, авторизуйтесь или создайте новый аккаунт.");
        }
    };
    private final Map<String, String> is = new HashMap<>() {{
        put("CancelledAction", "Aðgerðinni hefur verið hætt.");
        put("LoginPasswordNull", "Innskráning og lykilorð mega ekki vera tóm.");
        put("Serialize", "Serialization villa.");
        put("ServerUnavailable", "Þjónninn er tímabundið ófáanlegur.");
        put("NoAnswer", "Ekkert svar frá netþjóninum.");
        put("FileNotFound", "Skrá fannst ekki.");
        put("Script", "Script villa.");
        put("ScriptPath", "Slóðin að handritinu er ekki tilgreind.");
        put("ScriptRecursion", "Endurkoma greind í handriti.");
        put("ArgumentNull", "Færibreytan {0} má ekki vera núll.");
        put("IdNotFound", "Hlutur með þessu auðkenni fannst ekki.");
        put("DbDataGet", "Villa við að sækja gögn úr gagnagrunninum.");
        put("ArgumentValidation", "Недопустимое значение.\nОжидаемое значение {0}.");
        put("ArgumentType", "Ógilt gildi.\nBúist var við breytu af gerðinni {0}.");
        put("ReturnCalled", "Fara aftur til");
        put("ArgumentBad", "Ógilt gildi.");
        put("ScriptBad", "Ógilt handrit.");
        put("EndOfInput", "Lok inntaks.");
        put("EndOfFile", "Lok skráar.");
        put("RowsNotFound", "Það eru engar línur sem passa við skilyrðið.");
        put("AddFailed", "Drekanum var ekki bætt við safnið vegna þess að verðmæti hans er minna en verðmæti stærsta þáttarins í safninu.");
        put("PasswordFailed", "Rangt lykilorð, vinsamlegast reyndu að skrá þig inn aftur.");
        put("LoginFailed", "Það er enginn notandi með þessa innskráningu.\nSkráðu þig.");
        put("AuthFailed", "Staðfesting notendagagna mistókst.");
        put("LoginAlreadyExists", "Notandi með þessa innskráningu er þegar til.");
        put("RegistrationFailed", "Misheppnuð notendaskráning.");
        put("DbDataRemove", "Villa við að eyða dreka úr gagnagrunni.");
        put("UserHaveNoObjects", "Það eru engir hlutir í safninu sem tilheyra þessum notanda. Söfnunin var ekki hreinsuð.");
        put("DbClear", "Villa við að hreinsa gagnagrunn.");
        put("DbWrite", "Villa við að skrifa gögn í gagnagrunninn.");
        put("RejectModificate", "Hluturinn tilheyrir ekki þessum notanda og breyting hans er bönnuð.");
        put("NotEnoughRights", "Ófullnægjandi heimildir fyrir skrána.");
        put("DbConnection", "Tókst ekki að tengjast gagnagrunninum.");
        put("AuthNeeded", "Framkvæmd skipana er ekki í boði án leyfis. Vinsamlegast skráðu þig inn eða búðu til nýjan reikning.");
    }};
    private final Map<String, String> el = new HashMap<>(){{
        put("CancelledAction", "Η δράση ακυρώθηκε.");
        put("LoginPasswordNull", "Η σύνδεση και ο κωδικός πρόσβασης δεν μπορούν να είναι κενά.");
        put("Serialize", "Σφάλμα σειριοποίησης.");
        put("ServerUnavailable", "Ο διακομιστής δεν είναι προσωρινά διαθέσιμος.");
        put("NoAnswer", "Καμία απάντηση από τον διακομιστή.");
        put("FileNotFound", "Το αρχείο δεν βρέθηκε.");
        put("Script", "Σφάλμα σεναρίου.");
        put("ScriptPath", "Η διαδρομή της δέσμης ενεργειών δεν έχει καθοριστεί.");
        put("ScriptRecursion", "Εντοπίστηκε αναδρομή στο σενάριο.");
        put("ArgumentNull", "Το όρισμα {0} δεν μπορεί να είναι null.");
        put("IdNotFound", "Δεν βρέθηκε αντικείμενο με αυτό το αναγνωριστικό.");
        put("DbDataGet", "Σφάλμα ανάκτησης δεδομένων από τη βάση δεδομένων.");
        put("ArgumentValidation", "Μη έγκυρη τιμή.\nΑναμενόμενη τιμή {0}.");
        put("ArgumentType", "Μη έγκυρη τιμή.\nΑναμενόταν ένα όρισμα τύπου {0}.");
        put("ReturnCalled", "Επιστροφή προγράμματος.");
        put("ArgumentBad", "Απαράδεκτη τιμή.");
        put("ScriptBad", "Λάθος σενάριο.");
        put("EndOfInput", "Τέλος της καταχώρησης.");
        put("EndOfFile", "Τέλος του φακέλου.");
        put("RowsNotFound","Δεν υπάρχουν γραμμές που να ανταποκρίνονται στη συνθήκη.");
        put("AddFailed","Ο δράκος δεν προστέθηκε στη συλλογή επειδή η τιμή του είναι μικρότερη από την τιμή του μεγαλύτερου στοιχείου της συλλογής.");
        put("PasswordFailed","Μη έγκυρος κωδικός πρόσβασης, προσπαθήστε να συνδεθείτε ξανά.");
        put("LoginFailed","Ο χρήστης με αυτό το login δεν υπάρχει.\nΕγγραφείτε.");
        put("AuthFailed","Μη επιτυχής επικύρωση των δεδομένων χρήστη.");
        put("LoginAlreadyExists","Ένας χρήστης με αυτή τη σύνδεση υπάρχει ήδη.");
        put("RegistrationFailed","Μη επιτυχής εγγραφή χρήστη.");
        put("DbDataRemove","Σφάλμα διαγραφής ενός δράκου από τη βάση δεδομένων.");
        put("UserHaveNoObjects","Δεν υπάρχουν αντικείμενα που ανήκουν σε αυτόν τον χρήστη στη συλλογή. Η συλλογή δεν έχει εκκαθαριστεί.");
        put("DbClear","Σφάλμα καθαρισμού βάσης δεδομένων.");
        put("DbWrite", "Σφάλμα εγγραφής δεδομένων στη βάση δεδομένων.");
        put("RejectModificate", "Το αντικείμενο δεν ανήκει σε αυτόν τον χρήστη, η τροποποίησή του απαγορεύεται.");
        put("NotEnoughRights","Ανεπαρκή δικαιώματα στο αρχείο.");
        put("DbConnection","Απέτυχε να συνδεθεί στη βάση δεδομένων.");
        put("AuthNeeded","Οι εντολές δεν είναι διαθέσιμες χωρίς εξουσιοδότηση. Παρακαλούμε συνδεθείτε ή δημιουργήστε έναν νέο λογαριασμό.");
    }};
    private final Map<String, String> es_PR = new HashMap<>() {{
        put("CancelledAction", "La acción fue cancelada.");
        put("LoginPasswordNull", "El nombre de usuario y la contraseña no pueden estar en blanco.");
        put("Serialize", "Error de serialización.");
        put("ServerUnavailable", "El servidor no está disponible temporalmente.");
        put("NoAnswer", "No hay respuesta del servidor.");
        put("FileNotFound", "Archivo no encontrado.");
        put("Script", "Error de script.");
        put("ScriptPath", "No se especifica la ruta al script.");
        put("ScriptRecursion", "Se ha detectado recursión en el script.");
        put("ArgumentNull", "El argumento {0} no puede ser nulo.");
        put("IdNotFound", "No se ha encontrado un objeto con este identificador.");
        put("DbDataGet", "Error al recuperar datos de la base de datos.");
        put("ArgumentValidation", "Valor no válido.\nValor esperado {0}.");
        put("ArgumentType", "Valor no válido.\nSe esperaba un argumento de tipo {0}.");
        put("ReturnCalled", "Devoluciones solicitadas.");
        put("ArgumentBad", "Valor inaceptable.");
        put("ScriptBad", "Guión equivocado.");
        put("EndOfInput", "Fin de la entrada.");
        put("EndOfFile", "Fin del expediente.");
        put("RowsNotFound", "No hay filas que cumplan la condición.");
        put("AddFailed", "El dragón no se añadió a la colección porque su valor es menor que el valor del elemento más grande de la colección.");
        put("PasswordFailed", "Contraseña no válida, intente acceder de nuevo.");
        put("LoginFailed", "No hay ningún usuario con este nombre de usuario.\nRegístrate.");
        put("AuthFailed", "Validación fallida de los datos del usuario.");
        put("LoginAlreadyExists", "Ya existe un usuario con este nombre de usuario.");
        put("RegistrationFailed", "Registro de usuario fallido.");
        put("DbDataRemove", "Error al borrar un dragón de la base de datos.");
        put("UserHaveNoObjects", "No hay objetos pertenecientes a este usuario en la colección. No se ha borrado la colección.");
        put("DbClear", "Error de limpieza de la base de datos.");
        put("DbWrite", "Error al escribir datos en la base de datos.");
        put("RejectModificate", "El objeto no pertenece a este usuario, su modificación está prohibida.");
        put("NotEnoughRights", "Permisos insuficientes para el archivo.");
        put("DbConnection", "No se ha podido conectar a la base de datos.");
        put("AuthNeeded", "Los comandos no están disponibles sin autorización. Inicia sesión o crea una cuenta nueva.");
    }};

    @Override
    public String getString(String key) {
        switch (locale.getLanguage()) {
            case "ru" -> {
                return ru.getOrDefault(key, "error");
            }
            case "is" -> {
                return is.getOrDefault(key, "error");
            }
            case "el" -> {
                return el.getOrDefault(key, "error");
            }
            case "es" -> {
                return es_PR.getOrDefault(key, "error");
            }
            default -> {
                return "Error";
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
                return "Error";
            }
        }
    }
}
