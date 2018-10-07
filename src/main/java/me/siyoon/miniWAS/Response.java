package me.siyoon.miniWAS;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Response {
    private OutputStream out;
    private PrintWriter pw;

    public Response(OutputStream out, PrintWriter pw) {
        this.out = out;
        this.pw = pw;
    }

    public OutputStream getOut() {
        return out;
    }

    public PrintWriter getPw() {
        return pw;
    }
}
