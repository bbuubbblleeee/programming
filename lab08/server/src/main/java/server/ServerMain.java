package server;

import collectionManager.CollectionManager;
import collectionManager.DatabaseManager;
import commands.Command;
import exceptions.InvalidCommandException;
import serverCommands.ExitServer;
import transfer.Request;
import transfer.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

import static invoker.CommandsStorage.commands;
import static transfer.Serializer.deserialize;
import static transfer.Serializer.serialize;


public class ServerMain {
    private static final CollectionManager collectionManager = new DatabaseManager();
    private static InetSocketAddress inetSocketAddress;
    private static final ExecutorService recievePool = Executors.newCachedThreadPool();
    private static ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    private static final ExecutorService responsePool = Executors.newCachedThreadPool();
    private static int port = 1234;


    public static void main(String[] args) {
        Console consoleServer = new Console();
        consoleServer.start();
        try (DatagramChannel datagramChannel = DatagramChannel.open(); Selector selector = Selector.open()){
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(new InetSocketAddress(port));
            System.out.println("Сервер запущен...");
            datagramChannel.register(selector, SelectionKey.OP_READ);

            while (true){
                selector.select(100);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isReadable()) {
                        recievePool.execute(() -> {
                            Request request = null;
                            try {
                                RequestTask requestTask = recieveRequest(datagramChannel);
                                if (requestTask == null) {
                                    return;
                                }
                                inetSocketAddress = requestTask.clientAddress;
                                request = requestTask.request;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            ForkJoinTask<Response> task = forkJoinPool.submit(new Handler(request));
                            responsePool.execute(() -> {
                                try {
                                    Response response = task.join();
                                    sendResponse(response, datagramChannel);
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            });
                        });
                    }
                }
            }

        } catch (Exception e) {
        }
        finally {
            consoleServer.shutdown();
        }

    }

    public static DatabaseManager getCollectionManager() {
        return (DatabaseManager) collectionManager;
    }



    private static synchronized RequestTask recieveRequest(DatagramChannel datagramChannel) throws IOException, ClassNotFoundException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(65536);
        byteBuffer.clear();
        InetSocketAddress temp = (InetSocketAddress) datagramChannel.receive(byteBuffer);
        if (temp == null){
            return null;
        }
        inetSocketAddress = temp;
        byteBuffer.flip(); // переходим от записи в буфер к чтению
        Object object = deserialize(byteBuffer.array());
        if (object instanceof Request request) {
            return new RequestTask(request, inetSocketAddress);
        }
        return null;
    }

    private static synchronized void sendResponse(Response response, DatagramChannel datagramChannel) throws IOException {
        if (response == null || inetSocketAddress == null) return;
        ByteBuffer byteBuffer = ByteBuffer.allocate(65536);
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


//    private static synchronized Response handler(Request request) throws IOException {
//        try {
//            Command command = findCommand(request.command());
//            String[] arguments = request.args();
//
//            if (request.login() == null && request.password() == null){
//                throw new LoginUserException("Выполнение команд недоступно без авторизации. Пожалуйста, авторизуйтесь или создайте новый аккаунт.");
//            }
//            User user = new User(request.login(), request.password());
//
//            if (arguments.length != command.getRequiredArgs()) {
//                throw new WrongNumberOfArguments();
//            }
//
//            Invoker invoker = new Invoker();
//            return invoker.executeCommand(request);
//        } catch (Exception e) {
//            return new Response(e.getMessage());
//        }
//    }

    private static class Console extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (running) {
                    String commandStr = bufferedReader.readLine();
                    if (commandStr == null) continue;

                    switch (commandStr.trim().toLowerCase()) {
                        case "exit" -> {
                            new ExitServer().execute();
                            shutdown();
                            return;
                        }
                        default -> System.out.println("Введена неверная команда.");
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка чтения консоли: " + e.getMessage());
            }
        }

        public void shutdown() {
            running = false;
            this.interrupt();
        }
    }
}
