package me.siyoon.miniWAS;

public class Main {
    public static void main(String[] args) {
        System.out.println("WAS 시작1");
        Connector connector = new Connector(8888);
        connector.run();
        System.out.println("WAS 시작2");
    }
}
