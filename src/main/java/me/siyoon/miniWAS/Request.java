package me.siyoon.miniWAS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private InputStream in;
    private BufferedReader br;
    private String httpMethod;
    private String path;
    private Map<String, String> parameter;
    private Map<String, String> headerInfo;

    public Request(InputStream in, BufferedReader br) {
        try {
            String[] requestLine = br.readLine().split(" ");
            httpMethod = requestLine[0];
            path = requestLine[1];
            if (path.contains("?")) { //패스에 파라미터가 있다면
                int pos = path.indexOf("?");
                String pathParam = path.substring(pos+1); //?뒤쪽이 파라미터

                //파라미터 저장
                parameter = new HashMap<String, String>();
                if (pathParam.contains("&")) { //파라미터가 두개 이상이라면 (&로 구분)
                    String[] params = pathParam.split("&");
                    for (String s : params) {
                        System.out.println(s);
                        if (s.contains("=")) {
                            int paramPos = s.indexOf("=");
                            parameter.put(s.substring(0, paramPos), s.substring(paramPos + 1));
                        }
                    }
                } else { //파라미터가 한개라면
                    int paramPos = pathParam.indexOf("=");
                    parameter.put(pathParam.substring(0, paramPos), pathParam.substring(paramPos + 1));
                }

                path = path.substring(0,pos); //?앞까지만 잘라서 패스로 저장
            }

            String headerLine = null; //이렇게 해놔야 br.readLine()으로 계속 날려먹는 일이 없겠구나!!
            headerInfo = new HashMap<String, String>();
            while ((headerLine = br.readLine()) != null) {
                if(headerLine.equals("")) {
                    break; //빈줄이라면 종료
                }
                int pos = headerLine.indexOf(":");
                headerInfo.put(headerLine.substring(0,pos), headerLine.substring(pos+1));
            }

//            System.out.println(httpMethod);
//            System.out.println(path);
//            System.out.println(parameter.toString());
//            System.out.println(headerInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//Constructor

    //Getters
    public InputStream getIn() {
        return in;
    }

    public BufferedReader getBr() {
        return br;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getParameter() {
        return parameter;
    }

    public Map<String, String> getHeaderInfo() {
        return headerInfo;
    }
}
