package dao;

import collection.Dragon;
import com.google.gson.*;
import exceptions.FilePermissionException;
import exceptions.InvalidFileException;
import invoker.GsonHelper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * The class is responsible for reading and writing a collection of {@link Dragon} objects to a file.
 * It uses Gson to serialize and deserialize data in JSON format.
 */
public class FileDao implements DAO {
    private static FileDao instance;
    private String filePath;
    private String date;

    private FileDao() {
        try {
            this.filePath = System.getenv("DRAGON_FILE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static FileDao getInstance() {
        if (instance == null) {
            instance = new FileDao();
        }
        return instance;
    }

    @Override
    public DateAndDragons get() {
        try {
            return readFile();
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("Файл не найден.");
        } catch (FilePermissionException b) {
            throw new InvalidFileException("Недостаточно прав к файлу.");
        }
    }

    @Override
    public void save(Set<Dragon> dragons) {
        try {
            writeInFile(dragons);
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("Файл не найден.");
        } catch (FilePermissionException b) {
            throw new FilePermissionException("Недостаточно прав к файлу.");
        }
    }


    public DateAndDragons readFile() throws FileNotFoundException, FilePermissionException {
        checkFileRead(this.filePath);
        StringBuilder json = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(this.filePath))) {
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Dragon.class, new GsonHelper())
                .setPrettyPrinting()
                .create();

        JsonObject jsons = JsonParser.parseString(json.toString()).getAsJsonObject();
        try {
            date = jsons.get("Дата инициализации коллекции").getAsString();
        } catch (NullPointerException e) {
            date = DateFormat.getDateTimeInstance().format(new Date());
        }
        TreeSet<Dragon> res = new TreeSet<>();

        if (jsons.get("Драконы").isJsonArray()) {
            JsonArray jsonArray = jsons.get("Драконы").getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                try {
                    res.add(gson.fromJson(jsonElement, Dragon.class));
                } catch (InvalidFileException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
        return new DateAndDragons(date, res);

    }


    private void writeInFile(Set<Dragon> dragons) throws FileNotFoundException, FilePermissionException {
        checkFileWrite(this.filePath);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject fullJson = new JsonObject();
        fullJson.addProperty("Дата инициализации коллекции", date); // Добавляем дату
        fullJson.add("Драконы", gson.toJsonTree(dragons)); // Преобразуем список драконов в JSON
        String json = gson.toJson(fullJson);

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this.filePath, false))) {
            out.write(json.getBytes(StandardCharsets.UTF_8));
            out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkFileRead(String filePath) throws FileNotFoundException, FilePermissionException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не может быть пустым.");
        }
        File file = new File(filePath);
        if (!file.exists())
            throw new FileNotFoundException("Файл " + filePath + " не найден.");
        if (!file.canRead())
            throw new FilePermissionException("Недостаточно прав для чтение файла: " + filePath);
    }

    private void checkFileWrite(String filePath) throws FileNotFoundException, FilePermissionException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не может быть пустым.");
        }
        File file = new File(filePath);
        if (!file.exists())
            throw new FileNotFoundException("Файл " + filePath + " не найден.");
        if (!file.canWrite())
            throw new FilePermissionException("Недостаточно прав для чтение файла: " + filePath);
    }

    //TODO убрать из 6 лабы
//    public void saveLastId(Long id, Path filePath) throws FileNotFoundException, FilePermissionException {
//        checkFileWrite(filePath.toString());
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("Последний использованный идентификатор", id);
//        String json = gson.toJson(jsonObject);
//        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath.toString(), false))){
//            out.write(json.getBytes(StandardCharsets.UTF_8));
//            out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
//            out.flush();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public Long getLastId(Path filePath) throws FileNotFoundException, FilePermissionException {
//        checkFileRead(filePath.toString());
//        StringBuilder json = new StringBuilder();
//
//        try (Scanner scanner = new Scanner(new File(filePath.toString()))) {
//            while (scanner.hasNextLine()) {
//                json.append(scanner.nextLine());
//            }
//        }
//
//        JsonObject jsons = JsonParser.parseString(json.toString()).getAsJsonObject();
//        return jsons.get("Последний использованный идентификатор").getAsLong();
//    }


}
