package server;

import exceptions.SerializeException;
import transfer.Request;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.BlockingQueue;

import static transfer.Serializer.deserialize;

public class ClientReader implements Runnable{
    private DatagramChannel datagramChannel;
    private BlockingQueue<RequestTask> requestQueue;
    private InetSocketAddress inetSocketAddress;
    public ClientReader(DatagramChannel datagramChannel, BlockingQueue<RequestTask> requestQueue){
        this.datagramChannel = datagramChannel;
        this.requestQueue = requestQueue;
    }

    @Override
    public void run(){
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(8096);
            while (!Thread.currentThread().isInterrupted()){
                byteBuffer.clear();

                synchronized (datagramChannel) {
                    inetSocketAddress = (InetSocketAddress) datagramChannel.receive(byteBuffer);
                }
                if (inetSocketAddress == null) {
                    Thread.sleep(100); // Небольшая пауза, чтобы не грузить CPU
                    continue;
                }
                byteBuffer.flip(); // переходим от записи в буфер к чтению
                byte[] rawBytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(rawBytes);
                Object object = deserialize(rawBytes);
                if (object instanceof Request request) {
                    System.out.println(request);
                    requestQueue.put(new RequestTask(request, inetSocketAddress));
                }
                else {
                    throw new SerializeException("Ошибка десериализации.");
                }
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
