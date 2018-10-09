package me.siyoon.miniWAS;

import java.io.*;

public class DefaultServlet {
    public void Service(Request request, Response response) {
        OutputStream out = response.getOut();
        PrintWriter pw = response.getPw();

        String path = request.getPath();
        if (path.equals("/")) { //패스가 루트만 요청한다면
            path += "index.html";
        }

        File file = new File("webapps" + path);
        FileInputStream fis = null;
        if (file.exists()) {
            //HTTP 응답 프로토콜 전송 시작
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html; charset=UTF-8");
            pw.println("Content-Length: " + file.length());
            pw.println("");
            pw.flush();

            //바디부분
            try {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int readCount = 0;
                while ((readCount = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, readCount);
                    out.flush();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {

            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//if문
        else {
            pw.println("HTTP/1.1 404 NOT FOUND");
            pw.println("Content-Type: text/html; charset=UTF-8");
            pw.println("");
            pw.flush();
        }


    }
}
