package com.grush.larcas;

public class GameStart {
    static boolean isStarted = false;


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
            world.setBlock(x, 0, StoneBlock.class);
        }
    }
}
