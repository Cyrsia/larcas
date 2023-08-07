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

        for (int x = 0; x < size[0]; x++) {
            int n = (int) Math.sqrt(random.nextInt(1000)/100);
            for (int y = 0; y < n; y++) {
                world.setBlock(x, y, StoneBlock.class);
            }

        }
    }
}
