package clientMain;

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
    //TODO закрыть канал
    private int port = 1234;
    private SocketAddress serverAddress;
    private DatagramChannel channel;

    public Client() {
        try {
            serverAddress = new InetSocketAddress("localhost", port);
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.connect(serverAddress);
            System.out.println("Клиент создан.");
        } catch (IOException e) {
            System.out.println("Ошибка в создании клиента.");
        }
    }

    public void sendRequest(Request request) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(65535);
        byteBuffer = ByteBuffer.wrap(serialize(request));
        channel.write(byteBuffer);
    }

    public Response recieveResponse() throws IOException, ClassNotFoundException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(65535);
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
            return new Response("Ошибка: получен неожиданный тип объекта.");
        }
        return new Response("Ошибка: нет ответа от сервера.");
    }


}
