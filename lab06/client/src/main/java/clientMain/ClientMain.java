package clientMain;

import client.ReadData;
import io.ConsoleReader;
import io.MyReader;
import transfer.Request;

import java.io.IOException;

public class ClientMain {
    private static final Client client = new Client();

    public static void main(String[] args) {
        try (MyReader myReader = new ConsoleReader(); ReadData readData = new ReadData()) {
            while (myReader.hasNextLine()) {
                Handler handler = new Handler(myReader.readLine(), readData);
                Request request = handler.getRequest();
                if (request != null) {
                    System.out.println(sendAndGetResponse(request));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String sendAndGetResponse(Request request) throws IOException, ClassNotFoundException {
        client.sendRequest(request);
        return client.recieveResponse().toString();
    }
    public static Client getClient(){
        return client;
    }
}