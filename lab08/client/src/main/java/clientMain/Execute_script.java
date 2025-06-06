package clientMain;

import client.ReadData;
import exceptions.InvalidFileException;
import transfer.Request;

public class Execute_script {

    public Execute_script(String filePath) throws InvalidFileException {
        try (ReadData readData = new ReadData(filePath)) {
            while (readData.myReader.hasNextLine()) {
                String command = readData.myReader.readLine();
                Handler handler = new Handler(command, readData);
                Request request = handler.getRequest();
                if (request == null){
                    throw new InvalidFileException("Ошибка скрипта.");
                }
                System.out.println(ClientMain.sendAndGetResponse(request) + "\n");
            }
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
        System.out.println("Скрипт был исполнен.");

    }
}
