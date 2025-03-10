package commands;

import transfer.Request;
import transfer.Response;


public class  Add extends Command {
    public Add() {
        super("add", "adds new item to the collection", 0, 1);
    }

    @Override
    public Response execute(Request request) {
        collectionManager.add(request.dragons().get(0));
        return new Response("Dragon successfully added.");
    }
}
