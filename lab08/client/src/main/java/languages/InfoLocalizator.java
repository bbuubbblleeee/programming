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
            put("Owner", "владелец");
            put("TypeAge", "Введите возраст");
            put("TypeId", "Введите id");
            put("TypePath", "Введите путь к скрипту");
            put("ScriptPath", "путь к скрипту");
            put("TableShow", "Коллекция представлена в таблице.");
            put("RegistrationSuccess", "Пользователь успешно зарегистрирован.\nВойдите в аккаунт.");


            put("HelpCommand", " - add: добавляет новый элемент в коллекцию.\n" +
                    " - add_if_max: добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.\n" +
                    " - clear: очищает коллекцию.\n" +
                    " - count_by_age: выводит количество элементов, значение поля age которых равно заданному" +
                    " - execute_script: считывает и исполняет скрипт из указанного файла.\n" +
                    " - exit: завершает программу." +
                    " - help: выводит справку по доступным командам.\n" +
                    " - info: выводит информацию о коллекции.\n" +
                    " - print_field_descending_character: выводит значения поля character всех элементов в порядке убывания.\n" +
                    " - remove_any_by_age: удаляет из коллекции один элемент, значение поля age которого эквивалентно заданному.\n" +
                    " - remove_by_id: удаляет элемент из коллекции по его идентификатору.\n" +
                    " - remove_greater: удаляет из коллекции все элементы, превышающие заданный.\n" +
                    " - remove_lower: удаляет из коллекции все элементы, меньшие, чем заданный.\n" +
                    " - show: показывает элементы коллекции.\n" +
                    " - update: обновляет значение элемента коллекции, идентификатор которого равен заданному.");
            put("DragonToString", "Идентификатор = {0}\n" +
                    "Имя = {1}\n" +
                    "Координаты = (x = {2}, y = {3})\n" +
                    "Дата инициализации = {4}\n" +
                    "Возраст = {5}\n" +
                    "Цвет = {6}\n" +
                    "Тип = {7}\n" +
                    "Характер = {8}\n" +
                    "Пещера = (Глубина = {9}, Количество сокровищ = {10}\n" +
                    "Владелец = {11}\n");
            put("InfoCommand", "Информация о коллекции.\nТип: Dragon.\nДата инициализации: {0}.\nКоличество элементов: {1}.");
            put("CountByAgeCommand", "Количество элементов, значение поля age которых равно {0}: {1}.");
            put("UpdateSuccess", "Элемент успешно обновлен.");
            put("RemoveSuccess", "Элемент успешно удален.");
            put("AddSuccess", "Дракон был успешно добавлен.");
            put("ClearSuccess", "Коллекция очищена.");
            put("CollectionEmpty", "Коллекция пуста, выполнение этой команды не имеет смысла.");
            put("CharacterFieldEmpty", "Поле характер пусто у всех элементов коллекции.");
            put("AgeNotFound", "Элемент с возрастом = {0} не был найден.");
            put("RemoveGreaterSuccess", "Все элементы, превышающие заданный, были удалены из коллекции.");
            put("RemoveLowerSuccess", "Все элементы, меньшие заданного, были удалены из коллекции.");
            put("AddIfMaxNotFound", "Дракон не был добавлен в коллекцию, так как его значение меньше значения наибольшего элемента коллекции.");
            put("TypeName", "Введите имя");
            put("TypeColor", "Введите цвет");
            put("TypeType", "Введите тип");
            put("TypeCharacter", "Введите характер");
            put("TypeOwner", "Введите владельца");
            put("TypeX", "Введите координату x");
            put("TypeY", "Введите координату y");
            put("TypeDate", "Введите дату");
            put("TypeDepth", "Введите глубину");
            put("TypeTreasures", "Введите количество сокровищ");
            put("IdNotFound", "Объект с таким идентификатором не был найден.");
            put("NameNotFound", "Объект с таким именем не был найден.");
            put("ColorNotFound", "Объект с таким цветом не был найден.");
            put("CharacterNotFound", "Объект с таким характером не был найден.");
            put("TypeNotFound", "Объект с таким типом не был найден.");
            put("OwnerNotFound", "Объект с таким владельцем не был найден.");
            put("XNotFound", "Объект с такой координатой x не был найден.");
            put("YNotFound", "Объект с такой координатой y не был найден.");
            put("DateNotFound", "Объект с такой датой y не был найден.");
            put("DepthNotFound", "Объект с такой глубиной не был найден.");
            put("TreasuresNotFound", "Объект с таким количеством сокровищ не был найден.");







            //TODO добавить в другие языки


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
            put("Owner", "Eigandi");
            put("TypeAge", "Sláðu inn aldur þinn");
            put("TypeId", "Sláðu inn auðkenni");
            put("TypePath", "Sláðu inn slóðina að handritinu");
            put("TableShow", "Safnið er sett fram í töflunni.");
            put("RegistrationSuccess", "Notandi hefur verið skráður.\nSkráðu þig inn á reikninginn þinn.");
            put("ScriptPath", "leið að handriti");

            put("HelpCommand", " - add: bætir nýjum hlut í safnið.\n" +
                    " - add_if_max: bætir nýjum þætti við safn ef verðmæti hans er meira en verðmæti stærsta þáttarins í því safni.\n" +
                    " - clear: hreinsar safnið.\n" +
                    " - count_by_age: gefur út fjölda þátta þar sem gildi aldurssviðs er jafnt tilgreindu gildi" +
                    " - execute_script: les og keyrir skriftu úr tilgreindri skrá.\n" +
                    " - exit: klárar prógrammið." +
                    " - help: sýnir hjálp við tiltækar skipanir.\n" +
                    " - info: birtir upplýsingar um safnið.\n" +
                    " - print_field_descending_character: gefur út gildi stafasviðs allra þátta í lækkandi röð.\n" +
                    " - remove_any_by_age: fjarlægir einn þátt úr safninu þar sem gildi aldurssviðs er jafnt því sem gefið er upp.\n" +
                    " - remove_by_id: fjarlægir frumefni úr safni eftir auðkenni þess.\n" +
                    " - remove_greater: fjarlægir alla þætti úr safni sem fara yfir tiltekið gildi.\n" +
                    " - remove_lower: fjarlægir alla þætti úr safni sem eru minni en tiltekin tala.\n" +
                    " - show: sýnir safngripi.\n" +
                    " - update: uppfærir gildi safnþáttar þar sem auðkenni er jafnt tilgreindu.");

            put("DragonToString", "Auðkenni = {0}\n" +
                    "Nafn = {1}\n" +
                    "Hnit = (x = {2}, y = {3})\n" +
                    "Frumsetningardagsetning = {4}\n" +
                    "Aldur = {5}\n" +
                    "Litur = {6}\n" +
                    "Tegund = {7}\n" +
                    "Karakter = {8}\n" +
                    "Hellir = (Dýpt = {9}, Magn af fjársjóði = {10}\n" +
                    "Eigandi = {11}\n");
            put("InfoCommand", "Söfnunarupplýsingar.\nTegund: Dragon.\nFrumstillingardagur: {0}.\nFjöldi þátta: {1}.");
            put("CountByAgeCommand", "Fjöldi þátta þar sem gildi aldurssviðs er jafnt og {0}: {1}.");
            put("UpdateSuccess", "Atriðið hefur verið uppfært með góðum árangri.");
            put("RemoveSuccess", "Atriðinu var eytt.");
            put("AddSuccess", "Drekanum hefur verið bætt við.");
            put("ClearSuccess", "Safnið hefur verið hreinsað.");
            put("CollectionEmpty", "Safnið er tómt, það þýðir ekkert að framkvæma þessa skipun.");
            put("CharacterFieldEmpty", "Stafnareiturinn er tómur fyrir alla þætti safnsins.");
            put("AgeNotFound", "Eining með aldur = {0} fannst ekki.");
            put("RemoveGreaterSuccess", "Allir þættir sem fóru yfir tilgreint gildi voru fjarlægðir úr safninu.");
            put("RemoveLowerSuccess", "Allir þættir sem voru minni en tilgreint gildi voru fjarlægðir úr safninu.");
            put("AddIfMaxNotFound", "Drekanum var ekki bætt við safnið vegna þess að verðmæti hans er minna en verðmæti stærsta þáttarins í safninu.");

            put("TypeName", "Sláðu inn nafnið þitt");
            put("TypeColor", "Sláðu inn lit");
            put("TypeType", "Sláðu inn tegund");
            put("TypeCharacter", "Sláðu inn staf");
            put("TypeOwner", "Sláðu inn eiganda");
            put("TypeX", "Sláðu inn x hnitið");
            put("TypeY", "Sláðu inn y hnitið");
            put("TypeDate", "Sláðu inn dagsetningu");
            put("TypeDepth", "Sláðu inn dýpt");
            put("TypeTreasures", "Sláðu inn magn fjársjóða");

            put("IdNotFound", "Hlutur með þessu auðkenni fannst ekki.");
            put("NameNotFound", "Hlutur með þessu nafni fannst ekki.");
            put("ColorNotFound", "Enginn hlutur með þessum lit fannst.");
            put("CharacterNotFound", "Hlutur af þessu tagi fannst ekki.");
            put("TypeNotFound", "Enginn hlutur af þessari gerð fannst.");
            put("OwnerNotFound", "Enginn hlutur með þessum eiganda fannst.");
            put("XNotFound", "Enginn hlutur með þetta x-hnit fannst.");
            put("YNotFound", "Enginn hlutur með þetta y-hnit fannst.");
            put("DateNotFound", "Hlutur með slíkri dagsetningu y fannst ekki.");
            put("DepthNotFound", "Hlutur með slíka dýpt hefur ekki fundist.");
            put("TreasuresNotFound", "Hlutur með slíkt magn af fjársjóði hefur aldrei fundist.");

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
            put("Owner", "Ιδιοκτήτης");
            put("TypeAge", "Εισάγετε την ηλικία");
            put("TypeId", "Εισάγετε id");
            put("TypePath", "Εισάγετε τη διαδρομή του σεναρίου");
            put("TableShow", "Η συλλογή συνοψίζεται στον πίνακα.");
            put("RegistrationSuccess", "Ο χρήστης έχει εγγραφεί επιτυχώς.\nΣυνδεθείτε στο λογαριασμό σας.");
            put("ScriptPath", "διαδρομή προς το σενάριο");


            put("HelpCommand", " - add: προσθέτει ένα νέο στοιχείο στη συλλογή.\n" +
                    " - add_if_max: προσθέτει ένα νέο στοιχείο σε μια συλλογή εάν η αξία του είναι μεγαλύτερη από την αξία του μεγαλύτερου στοιχείου σε αυτήν τη συλλογή.\n" +
                    " - clear: καθαρίζει τη συλλογή.\n" +
                    " - count_by_age: εξάγει τον αριθμό των στοιχείων των οποίων η τιμή πεδίου ηλικίας είναι ίση με την καθορισμένη τιμή" +
                    " - execute_script: διαβάζει και εκτελεί ένα σενάριο από το καθορισμένο αρχείο.\n" +
                    " - exit: ολοκληρώνει το πρόγραμμα." +
                    " - help: εμφανίζει βοήθεια για τις διαθέσιμες εντολές.\n" +
                    " - info: εμφανίζει πληροφορίες για τη συλλογή.\n" +
                    " - print_field_descending_character: εξάγει τις τιμές του πεδίου χαρακτήρων όλων των στοιχείων με φθίνουσα σειρά.\n" +
                    " - remove_any_by_age: αφαιρεί ένα στοιχείο από τη συλλογή του οποίου η τιμή πεδίου ηλικίας είναι ίση με τη δεδομένη.\n" +
                    " - remove_by_id: αφαιρεί ένα στοιχείο από μια συλλογή με το αναγνωριστικό του.\n" +
                    " - remove_greater: αφαιρεί όλα τα στοιχεία από μια συλλογή που υπερβαίνουν μια δεδομένη τιμή.\n" +
                    " - remove_lower: αφαιρεί όλα τα στοιχεία από μια συλλογή που είναι μικρότερα από έναν δεδομένο αριθμό.\n" +
                    " - show: εμφανίζει είδη συλλογής.\n" +
                    " - update: ενημερώνει την τιμή ενός στοιχείου συλλογής του οποίου το αναγνωριστικό είναι ίσο με το καθορισμένο.");

            put("DragonToString", "Αναγνωριστικό = {0}\n" +
                    "Ονομα = {1}\n" +
                    "Συντεταγμένες = (x = {2}, y = {3})\n" +
                    "Ημερομηνία αρχικοποίησης = {4}\n" +
                    "Ηλικία = {5}\n" +
                    "Χρώμα = {6}\n" +
                    "Τύπος = {7}\n" +
                    "Χαρακτήρας = {8}\n" +
                    "Σπήλαιο = (Bάθος = {9}, Αριθμός θησαυρών = {10}\n" +
                    "Ιδιοκτήτης = {11}\n");
            put("InfoCommand", "Πληροφορίες συλλογής.\nτύπος: Dragon.\nΗμερομηνία αρχικοποίησης: {0}.\nΑριθμός στοιχείων: {1}.");
            put("CountByAgeCommand", "Ο αριθμός των στοιχείων των οποίων η τιμή πεδίου ηλικίας είναι ίση με {0}: {1}.");
            put("UpdateSuccess", "Το στοιχείο ενημερώθηκε με επιτυχία.");
            put("RemoveSuccess", "Το στοιχείο διαγράφηκε με επιτυχία.");
            put("AddSuccess", "Ο δράκος προστέθηκε με επιτυχία.");
            put("ClearSuccess", "Η συλλογή έχει εκκαθαριστεί.");
            put("CollectionEmpty", "Η συλλογή είναι άδεια, η εκτέλεση αυτής της εντολής δεν έχει νόημα.");
            put("CharacterFieldEmpty", "Το πεδίο χαρακτήρων είναι κενό για όλα τα στοιχεία της συλλογής.");
            put("AgeNotFound", "Το στοιχείο με ηλικία = {0} δεν βρέθηκε.");
            put("RemoveGreaterSuccess", "Όλα τα στοιχεία που υπερβαίνουν την καθορισμένη τιμή αφαιρέθηκαν από τη συλλογή.");
            put("RemoveLowerSuccess", "Όλα τα στοιχεία μικρότερα από την καθορισμένη τιμή αφαιρέθηκαν από τη συλλογή.");
            put("AddIfMaxNotFound", "Ο δράκος δεν προστέθηκε στη συλλογή επειδή η αξία του είναι μικρότερη από την αξία του μεγαλύτερου στοιχείου της συλλογής.");

            put("TypeName", "Εισαγάγετε το όνομά σας");
            put("TypeColor", "Εισαγάγετε χρώμα");
            put("TypeType", "Εισαγάγετε τύπο");
            put("TypeCharacter", "Εισαγάγετε χαρακτήρα");
            put("TypeOwner", "Εισαγάγετε τον ιδιοκτήτη");
            put("TypeX", "Εισαγάγετε τη συντεταγμένη x");
            put("TypeY", "Εισαγάγετε τη συντεταγμένη y");
            put("TypeDate", "Εισαγάγετε ημερομηνία");
            put("TypeDepth", "Εισαγάγετε βάθος");
            put("TypeTreasures", "Εισαγάγετε την ποσότητα των θησαυρών");
            put("IdNotFound", "Δεν βρέθηκε αντικείμενο με αυτό το αναγνωριστικό.");
            put("NameNotFound", "Δεν βρέθηκε αντικείμενο με αυτό το όνομα.");
            put("ColorNotFound", "Δεν βρέθηκε αντικείμενο με αυτό το χρώμα.");
            put("CharacterNotFound", "Δεν βρέθηκε αντικείμενο αυτής της φύσης.");
            put("TypeNotFound", "Δεν βρέθηκε αντικείμενο αυτού του τύπου.");
            put("OwnerNotFound", "Δεν βρέθηκε αντικείμενο με αυτόν τον ιδιοκτήτη.");
            put("XNotFound", "Δεν βρέθηκε αντικείμενο με αυτή τη συντεταγμένη x.");
            put("YNotFound", "Δεν βρέθηκε αντικείμενο με αυτή τη συντεταγμένη y.");
            put("DateNotFound", "Το αντικείμενο με τέτοια ημερομηνία y δεν βρέθηκε.");
            put("DepthNotFound", "Δεν έχει βρεθεί αντικείμενο με τέτοιο βάθος.");
            put("TreasuresNotFound", "Ένα αντικείμενο με τέτοια ποσότητα θησαυρού δεν έχει βρεθεί ποτέ.");

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
            put("Owner", "Dueño");
            put("TypeAge", "Introduzca la edad");
            put("TypeId", "Introduzca un identificador");
            put("TypePath", "Introduzca la ruta del script");
            put("TableShow", "La recopilación se resume en el cuadro.");
            put("RegistrationSuccess", "El usuario se ha registrado correctamente.\nAcceda a su cuenta.");
            put("ScriptPath", "ruta al script");

            put("HelpCommand", " - add: añade un nuevo artículo a la colección.\n" +
                    " - add_if_max: agrega un nuevo elemento a una colección si su valor es mayor que el valor del elemento más grande de esa colección.\n" +
                    " - clear: borra la colección.\n" +
                    " - count_by_age: Muestra el número de elementos cuyo valor del campo de edad es igual al valor especificado" +
                    " - execute_script: Lee y ejecuta un script desde el archivo especificado.\n" +
                    " - exit: completa el programa." +
                    " - help: muestra ayuda sobre los comandos disponibles.\n" +
                    " - info: muestra información sobre la colección.\n" +
                    " - print_field_descending_character: genera los valores del campo de carácter de todos los elementos en orden descendente.\n" +
                    " - remove_any_by_age: elimina un elemento de la colección cuyo valor del campo de edad sea igual al indicado.\n" +
                    " - remove_by_id: elimina un elemento de una colección por su id.\n" +
                    " - remove_greater: elimina todos los elementos de una colección que exceden un valor determinado.\n" +
                    " - remove_lower: elimina todos los elementos de una colección que sean menores a un número determinado.\n" +
                    " - show: muestra artículos de colección.\n" +
                    " - update: actualiza el valor de un elemento de colección cuyo ID es igual al especificado.");

            put("DragonToString", "Identificador = {0}\n" +
                    "Nombre = {1}\n" +
                    "Coordenadas = (x = {2}, y = {3})\n" +
                    "Fecha de inicialización = {4}\n" +
                    "Edad = {5}\n" +
                    "Color = {6}\n" +
                    "Tipo = {7}\n" +
                    "Personaje = {8}\n" +
                    "Cueva = (Profundidad = {9}, Cantidad de tesoro = {10}\n" +
                    "Dueño = {11}\n");
            put("InfoCommand", "Información de la colección.\nTipo: Dragon.\nFecha de inicialización: {0}.\nNúmero de elementos: {1}.");
            put("CountByAgeCommand", "El número de elementos cuyo valor del campo de edad es igual a {0}: {1}.");
            put("UpdateSuccess", "El artículo se ha actualizado correctamente..");
            put("RemoveSuccess", "El artículo fue eliminado exitosamente");
            put("AddSuccess", "El dragón se ha añadido correctamente.");
            put("ClearSuccess", "La colección ha sido limpiada.");
            put("CollectionEmpty", "La colección está vacía, ejecutar este comando no tiene sentido.");
            put("CharacterFieldEmpty", "El campo de carácter está vacío para todos los elementos de la colección.");
            put("AgeNotFound", "No se encontró el elemento con edad = {0}.");
            put("RemoveGreaterSuccess", "Todos los elementos que excedían el valor especificado fueron eliminados de la colección.");
            put("RemoveLowerSuccess", "Se eliminaron de la colección todos los elementos menores al valor especificado.");
            put("AddIfMaxNotFound", "El dragón no fue agregado a la colección porque su valor es menor que el valor del elemento más grande de la colección.");

            put("TypeName", "Introduce tu nombre");
            put("TypeColor", "Introducir color");
            put("TypeType", "Introduzca el tipo");
            put("TypeCharacter", "Introducir personaje");
            put("TypeOwner", "Introduzca el propietario");
            put("TypeX", "Introduzca la coordenada x");
            put("TypeY", "Introduzca la coordenada y");
            put("TypeDate", "Introducir fecha");
            put("TypeDepth", "Introduzca profundidad");
            put("TypeTreasures", "Ingrese la cantidad de tesoros");
            put("IdNotFound", "No se encontró un objeto con este identificador.");
            put("NameNotFound", "No se encontró un objeto con este nombre.");
            put("ColorNotFound", "No se encontró ningún objeto con este color.");
            put("CharacterNotFound", "No se encontró un objeto de esta naturaleza.");
            put("TypeNotFound", "No se encontró ningún objeto de este tipo.");
            put("OwnerNotFound", "No se encontró ningún objeto con este propietario.");
            put("XNotFound", "No se encontró ningún objeto con esta coordenada x.");
            put("YNotFound", "No se encontró ningún objeto con esta coordenada y.");
            put("DateNotFound", "No se encontró ningún objeto con esta fecha.");
            put("DepthNotFound", "No se ha encontrado un objeto con tanta profundidad.");
            put("TreasuresNotFound", "Nunca se ha encontrado un objeto con tal cantidad de tesoro.");


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
    public String getStringFormatted(String key, Object[] field){
        switch (locale.getLanguage()) {
            case "ru" -> {
                String pattern = ru.get(key);
                if (pattern == null){
                    return "info";
                }
                return MessageFormat.format(pattern, field);
            }
            case "is" -> {
                String pattern = is.get(key);
                if (pattern == null){
                    return "info";
                }
                return MessageFormat.format(pattern, field);
            }
            case "el" -> {
                String pattern = el.get(key);
                if (pattern == null){
                    return "info";
                }
                return MessageFormat.format(pattern, field);
            }
            case "es" -> {
                String pattern = es_PR.get(key);
                if (pattern == null){
                    return "info";
                }
                return MessageFormat.format(pattern, field);
            }
            default -> {
                return "Info";
            }
        }
    }
}
