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
import java.util.concurrent.RecursiveTask;

import static invoker.CommandsStorage.commands;
import static transfer.Serializer.serialize;

public class Handler extends RecursiveTask<Response> {
    private Request request;
    public Handler (Request request){
        this.request = request;
    }

    @Override
    protected Response compute() {
        try {
            Command command = findCommand(request.command());
            String[] arguments = request.args();

            if (request.login() == null && request.password() == null){
                throw new LoginUserException("AuthNeeded");
            }
            User user = new User(request.login(), request.password());

            if (arguments.length != command.getRequiredArgs()) {
                throw new WrongNumberOfArguments();
            }

            Invoker invoker = new Invoker();
            Response response = invoker.executeCommand(request);
            return response;
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    private static Command findCommand(String commandString) throws InvalidCommandException {
        Command command = commands.get(commandString);
        if (command == null) {
            throw new InvalidCommandException();
        }
        return command;
    }
}
