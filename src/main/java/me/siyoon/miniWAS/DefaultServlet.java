package me.siyoon.miniWAS;

import java.io.OutputStream;
import java.io.PrintWriter;

public class DefaultServlet {
    public void Service(Request request, Response response) {
        OutputStream out = response.getOut();
        PrintWriter pw = response.getPw();

        String path = request.getPath();
        if (path.equals("/")) { //패스가 루트만 요청한다면
            path += "index.html";
        }


    }
}
