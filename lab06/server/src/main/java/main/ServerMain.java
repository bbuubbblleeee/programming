package main;

import collectionManager.CollectionManager;
import collectionManager.InMemoryCollection;
import commands.Command;
import serverCommands.ExitServer;
import exceptions.InvalidCommandException;
import exceptions.WrongNumberOfArguments;
import invoker.Invoker;
import serverCommands.Save;
import transfer.Request;
import transfer.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

import static invoker.CommandsStorage.commands;
import static transfer.Serializer.deserialize;
import static transfer.Serializer.serialize;


public class ServerMain {
    private static final CollectionManager collectionManager = new InMemoryCollection();
    private static InetSocketAddress inetSocketAddress;
    private static int port = 1234;


    public static void main(String[] args) {
        try (DatagramChannel datagramChannel = DatagramChannel.open(); Selector selector = Selector.open()){
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(new InetSocketAddress(port));
            System.out.println("Сервер запущен...");
            datagramChannel.register(selector, SelectionKey.OP_READ);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                selector.select(100);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isReadable()){
                        handler(datagramChannel);
                    }
                }

                while (bufferedReader.ready()){
                    String commandStr = bufferedReader.readLine();

                    if (commandStr == null){
                        continue;
                    }
                    switch(commandStr.trim().toLowerCase()){
                        case "save" -> new Save().execute();
                        case "exit" -> new ExitServer().execute();
                        default -> System.out.println("Неверная команда введена.");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }



    private static Request recieveRequest(DatagramChannel datagramChannel) throws IOException, ClassNotFoundException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8096);
        byteBuffer.clear();
        inetSocketAddress = (InetSocketAddress) datagramChannel.receive(byteBuffer);
        byteBuffer.flip(); // переходим от записи в буфер к чтению


        Object object = deserialize(byteBuffer.array());
        if (object instanceof Request request) {
            return request;
        }
        return null;
    }

    private static void sendResponse(Response response, DatagramChannel datagramChannel) throws IOException {
        if (response == null){
            return;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(8096);
        byteBuffer.clear();
        byteBuffer = ByteBuffer.wrap(serialize(response));
        datagramChannel.send(byteBuffer, inetSocketAddress);

    }


    private static Command findCommand(String commandString) throws InvalidCommandException {
        Command command = commands.get(commandString);
        if (command == null) {
            throw new InvalidCommandException();
        }
        return command;
    }

    private static void handler(DatagramChannel datagramChannel) throws IOException {
        try {
            Request request = recieveRequest(datagramChannel);
            Command command = findCommand(request.command());
            String[] arguments = request.args();

            if (arguments.length != command.getRequiredArgs()) {
                throw new WrongNumberOfArguments();
            }

            if (command.getName().equals("add") || command.getName().equals("add_if_max") || command.getName().equals("remove_lower") || command.getName().equals("remove_greater")){
                request.dragons().get(0).setIdAuto();
                request.dragons().get(0).setDateAuto();
            }

            Invoker invoker = new Invoker();
            Response response = invoker.executeCommand(request);
            sendResponse(response, datagramChannel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sendResponse(new Response(e.getMessage()), datagramChannel);
        }
    }
}