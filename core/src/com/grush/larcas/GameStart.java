package com.grush.larcas;

import java.util.Random;

public class GameStart {
    static boolean isStarted = false;
    Random random = new Random();


    final static GameStart INSTANCE = new GameStart();
    public void start() {
        if (isStarted) {
            LogMaster.INSTANCE.log("What the fuck are you doing?");
        } else {
            this.init();
        }
    }

    private void init(){
        LogMaster.INSTANCE.log("GameStart::init");
        World world = World.INSTANCE;
        int[] size = world.getSize();
    }
}
