package main;

import collectionManager.CollectionManager;
import collectionManager.InMemoryCollection;
import commands.Command;
import exceptions.InvalidCommandException;
import exceptions.WrongNumberOfArguments;
import invoker.Invoker;
import transfer.Request;
import transfer.Response;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

import static invoker.CommandsStorage.commands;
import static transfer.Serializer.deserialize;
import static transfer.Serializer.serialize;


public class ServerMain {
    private static final CollectionManager collectionManager = new InMemoryCollection();

    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(1234);
            byte[] buffer = new byte[65535];

            System.out.println("Сервер запущен...");


            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    datagramSocket.receive(packet);

                    byte[] realData = Arrays.copyOf(packet.getData(), packet.getLength());
                    System.out.println("Ща будет десериализация");
                    Request request = (Request) deserialize(realData);
                    System.out.println("Получен запрос от клиента: " + request.command());

                    Command command = findCommand(request.command());
                    String[] arguments = request.args();

                    if (arguments.length != command.getRequiredArgs()) {
                        throw new WrongNumberOfArguments();
                    }

                    Invoker invoker = new Invoker();
                    Response response = invoker.executeCommand(request);
                    System.out.println("Команда выполнена.");

                    byte[] dataToSend = serialize(response);
                    sendResponse(dataToSend, packet, datagramSocket);
                } catch (Exception e) {
                    sendResponse(serialize(new Response(e.getMessage())), packet, datagramSocket);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: сервер не запустился.");
        }
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    private static Command findCommand(String commandString) throws InvalidCommandException {
        Command command = commands.get(commandString);
        if (command == null) {
            throw new InvalidCommandException();
        }
        return command;
    }


    private static void sendResponse(byte[] dataToSend, DatagramPacket packet, DatagramSocket datagramSocket) throws IOException {
        DatagramPacket responsePacket = new DatagramPacket(
                dataToSend, dataToSend.length,
                packet.getAddress(), packet.getPort()
        );
        datagramSocket.send(responsePacket);

    }
}