package clientMain;

import client.ReadData;
import exceptions.InvalidFileException;

public class Execute_script {

    public Execute_script(String filePath) throws InvalidFileException {
        try (ReadData readData = new ReadData(filePath)) {
            while (readData.myReader.hasNextLine()) {
                String command = readData.myReader.readLine();
                System.out.println("Текущая команда: " + command + ".");
                Handler handler = new Handler(command, readData);
                System.out.println(ClientMain.sendAndGetResponse(handler.getRequest()));
            }
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
        System.out.println("Скрипт был исполнен.");

    }
}
