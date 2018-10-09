package me.siyoon.miniWAS;

public class Main {
    public static void main(String[] args) {
        System.out.println("WAS 시작");
        Connector connector = new Connector(8888);
        connector.run();
    }
}
