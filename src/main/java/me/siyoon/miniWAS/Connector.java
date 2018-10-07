package me.siyoon.miniWAS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector {
    private int port;

    public Connector(int port) {
        this.port = port;
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
