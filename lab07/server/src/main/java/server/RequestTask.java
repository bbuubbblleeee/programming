package server;

import transfer.Request;

import java.net.InetSocketAddress;

public class RequestTask {
    final Request request;
    final InetSocketAddress clientAddress;
    public RequestTask(Request req, InetSocketAddress addr) {
        this.request = req;
        this.clientAddress = addr;
    }

}
