package server;

import transfer.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import static transfer.Serializer.serialize;

public class SendResponse {
    private Response response;
    private DatagramChannel datagramChannel;
    private InetSocketAddress inetSocketAddress;

    public SendResponse(Response response, DatagramChannel datagramChannel, InetSocketAddress inetSocketAddress){
        this.response = response;
        this.datagramChannel = datagramChannel;
        this.inetSocketAddress = inetSocketAddress;
    }

    public void sendResponse(Response response, DatagramChannel datagramChannel, InetSocketAddress inetSocketAddress) throws IOException {
        if (response == null){
            return;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(8096);
        byteBuffer.clear();
        byteBuffer = ByteBuffer.wrap(serialize(response));
        datagramChannel.send(byteBuffer, inetSocketAddress);

    }
}
