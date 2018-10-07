package me.siyoon.miniWAS;

import java.io.*;
import java.net.Socket;

public class Handler extends Thread {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        BufferedReader br = null;
        OutputStream out =null;
        PrintWriter pw = null;

        try {
            in = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            out = socket.getOutputStream();
            pw = new PrintWriter(new OutputStreamWriter(out));

            Request request = new Request(in, br);
            Response response = new Response(out,pw);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
