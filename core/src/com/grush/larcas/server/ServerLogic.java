package com.grush.larcas.server;
public class ServerLogic {
    private ServerLogic(){
    }
    public static ServerLogic INSTANCE = new ServerLogic();

    public void update(){
        ServerVarField.worldChain.getEntityManager().updateAll();
    }
}
