package com.grush.larcas.server;

import com.grush.larcas.GameLogic;

public class ServerLogic {
    private ServerLogic(){
    }
    public static ServerLogic INSTANCE = new ServerLogic();

    public void update(){
        ServerVarField.worldChain.getEntityManager().updateAll();
    }
}
