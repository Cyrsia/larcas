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
        Player player = Player.PLAYER;
        if (player.overlaps(player.coordinate.x, player.coordinate.y)) {
            player.ghost = true;
        }
        int[] size = world.getSize();
    }
}
