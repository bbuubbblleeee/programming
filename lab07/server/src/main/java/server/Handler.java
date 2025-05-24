package server;

import commands.Command;
import exceptions.InvalidCommandException;
import exceptions.LoginUserException;
import exceptions.WrongNumberOfArguments;
import invoker.Invoker;
import transfer.Request;
import transfer.Response;
import users.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static invoker.CommandsStorage.commands;
import static transfer.Serializer.serialize;

public class Handler implements Runnable{
    private final BlockingQueue<RequestTask> requestQueue;
    private final DatagramChannel channel;

    public Handler(BlockingQueue<RequestTask> requestQueue, DatagramChannel channel) {
        this.requestQueue = requestQueue;
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                RequestTask task = requestQueue.take();
                ForkJoinPool.commonPool().submit(() -> {
                    Request request = task.request;
                    Command command = findCommand(request.command());
                    String[] arguments = request.args();

                    if (request.login() == null && request.password() == null){
                        throw new LoginUserException("Выполнение команд недоступно без авторизации. Пожалуйста, авторизуйтесь или создайте новый аккаунт.");
                    }
                    User user = new User(request.login(), request.password());

                    if (arguments.length != command.getRequiredArgs()) {
                        throw new WrongNumberOfArguments();
                    }

                    Invoker invoker = new Invoker();
                    try {
                        sendResponse(invoker.executeCommand(request), channel, task.clientAddress);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void sendResponse(Response response, DatagramChannel datagramChannel, InetSocketAddress inetSocketAddress) throws IOException {
        Executors.newCachedThreadPool().submit(() -> {
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(8096);
                byteBuffer.clear();
                byteBuffer = ByteBuffer.wrap(serialize(response));
                synchronized (channel) { // или через ReentrantLock
                    datagramChannel.send(byteBuffer, inetSocketAddress);
                }
            } catch (IOException e) {
                System.err.println("Ошибка отправки: " + e.getMessage());
            }
        });
    }

    private static Command findCommand(String commandString) throws InvalidCommandException {
        Command command = commands.get(commandString);
        if (command == null) {
            throw new InvalidCommandException();
        }
        return command;
    }
}
