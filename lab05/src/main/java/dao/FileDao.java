package dao;

import collection.Dragon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.TreeSet;

import com.google.gson.*;
import exceptions.FilePermissionException;
import exceptions.InvalidFileException;
import transfer.GsonHelper;


public class FileDao implements DAO {
    private static FileDao instance;
    private final String filePath = System.getenv("DRAGON_FILE");

    private FileDao() {}

    public static FileDao getInstance() {
        if (instance == null) {
            instance = new FileDao();
        }
        return instance;
    }

    @Override
    public String getPath(){
        return this.filePath;
    }
    @Override
    public TreeSet<Dragon> get() {
        try {
            return readFile();
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("File not found");
        } catch (FilePermissionException b) {
            throw new InvalidFileException("There is no permission to read the file");
        }
    }

    @Override
    public void save(TreeSet<Dragon> dragons) {
        try{
            writeInFile(dragons);
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("File not found");
        }
        catch (FilePermissionException b) {
            throw new InvalidFileException("There is no permission to read the file");
        }
    }

    public TreeSet<Dragon> readFile() throws FileNotFoundException, FilePermissionException {
        checkFileRead();
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

        JsonElement jsons = JsonParser.parseString(json.toString());
        TreeSet<Dragon> res = new TreeSet<Dragon>();

        if (jsons.isJsonArray()) {
            JsonArray jsonArray = jsons.getAsJsonArray();
            for (JsonElement js : jsonArray) {
                res.add(gson.fromJson(js, Dragon.class));
            }
        }
        return res;

    }


    private void writeInFile(TreeSet<Dragon> dragons) throws FileNotFoundException, FilePermissionException {
        checkFileWrite();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dragons);

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this.filePath, false))){
            out.write(json.getBytes(StandardCharsets.UTF_8));
            out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkFileRead() throws FileNotFoundException, FilePermissionException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        File file = new File(this.filePath);
        if (!file.exists())
            throw new FileNotFoundException("File " + this.filePath + " not found");
        if (!file.canRead())
            throw new FilePermissionException("There is no read permission for file " + this.filePath);
    }

    private void checkFileWrite() throws FileNotFoundException, FilePermissionException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        File file = new File(this.filePath);
        if (!file.exists())
            throw new FileNotFoundException("File " + this.filePath + " not found");
        if (!file.canWrite())
            throw new FilePermissionException("There is no read permission for file " + this.filePath);
    }


}
