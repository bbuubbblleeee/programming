package clientMain;

import client.ReadData;
import exceptions.InvalidFileException;
import languages.ErrorLocalizator;
import languages.Localizator;
import transfer.Request;

public class Execute_script {
    private StringBuilder result = new StringBuilder();
    private static Localizator errorLocalizator = ErrorLocalizator.getInstance();


    public void execute(String filePath) throws InvalidFileException{
        try (ReadData readData = new ReadData(filePath)) {
            while (readData.myReader.hasNextLine()) {
                String command = readData.myReader.readLine();
                Handler handler = new Handler(command, readData);
                Request request = handler.getRequest();

                if (request == null){
                    throw new InvalidFileException(errorLocalizator.getString("Script"));
                }

//                result.append("Текущая команда: ").append(command).append("\n");
                result.append(ClientMain.sendAndGetResponse(request)).append("\n\n");
            }
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    public String getResult() {
        return result.toString();
    }

}
