package com.grush.larcas;
public class LogMaster {
    public static final LogMaster INSTANCE = new LogMaster();
    public <T> void log(T message){
        System.out.print("[LOG] ");
        System.out.print(message);
        System.out.print("\n");
    }
    private LogMaster(){}
}
