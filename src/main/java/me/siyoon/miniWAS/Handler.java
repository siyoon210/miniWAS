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

            //동적 서블릿을 위한 패스가 있는경우
            //추후처리

            //동적 서블릿 패스가 아니면 (정적처리만 하면 되는 경우)
            DefaultServlet defaultServlet = new DefaultServlet();
            defaultServlet.Service(request, response);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();

            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
