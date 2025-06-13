package clientMain;

import languages.ErrorLocalizator;
import languages.Localizator;
import transfer.Request;
import transfer.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import static transfer.Serializer.deserialize;
import static transfer.Serializer.serialize;

public class Client {
    private static final int TIME_OUT = 5000;
    private int port = 1234;
    private SocketAddress serverAddress;
    private DatagramChannel channel;
    private Localizator errorLocalizator = ErrorLocalizator.getInstance();


    public Client() {
        try {
            serverAddress = new InetSocketAddress("localhost", port);
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.connect(serverAddress);
        } catch (IOException e) {
            System.out.println("Ошибка в создании клиента.");
        }
    }

    public void sendRequest(Request request) throws IOException {
        ByteBuffer.allocate(8096);
        ByteBuffer byteBuffer;
        byteBuffer = ByteBuffer.wrap(serialize(request));
        try {
            channel.write(byteBuffer);
        }
        catch (Exception e){
        }
    }

    public Response recieveResponse() throws IOException, ClassNotFoundException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8096);
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < TIME_OUT) {
            if (channel.receive(byteBuffer) == null) {
                continue;
            }
            byteBuffer.flip(); // переходим от записи в буфер к чтению

            byte[] data = new byte[byteBuffer.limit()];
            byteBuffer.get(data);

            Object object = deserialize(data);
            if (object instanceof Response response) {
                return response;
            }
            return new Response(errorLocalizator.getString("ArgumentBad"));
        }
        return new Response(errorLocalizator.getString("NoAnswer"));
    }

    public void close() throws IOException {
        channel.close();
    }


}
