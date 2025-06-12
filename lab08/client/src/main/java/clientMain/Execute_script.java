package clientMain;

import client.ReadData;
import exceptions.InvalidFileException;
import languages.ErrorLocalizator;
import languages.InfoLocalizator;
import languages.Localizator;
import transfer.Request;

import java.util.Arrays;

public class Execute_script {
    private StringBuilder result = new StringBuilder();
    private static Localizator errorLocalizator = ErrorLocalizator.getInstance();
    private static Localizator infoLocalizator = InfoLocalizator.getInstance();



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
                result.append(getStringResponse(ClientMain.sendAndGetResponse(request))).append("\n\n");
            }
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    public String getResult() {
        return result.toString();
    }
    private String getStringResponse(String response){
        String[] args = response.split("\\|");
        if (args.length > 1){
            Object[] arguments = Arrays.copyOfRange(args, 1, args.length);
            String error = errorLocalizator.getStringFormatted(args[0], arguments);
            if (!error.equals("error")){
                return error;
            }
            String info = infoLocalizator.getStringFormatted(args[0], arguments);
            if (!info.equals("info")){
                return info;
            }
        }

        String error = errorLocalizator.getString(args[0]);
        if (!error.equals("error")){
            return error;
        }
        return infoLocalizator.getString(args[0]);
    }
}
