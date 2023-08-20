package com.grush.larcas;

public class GameStart {
    static boolean isStarted = false;
    final static GameStart INSTANCE = new GameStart();
    public void start() {
        if (isStarted) {
            LogMaster.INSTANCE.log("What the fuck are you doing?");
        } else {
            isStarted = true;
            this.init();
        }
    }

    private void init(){
        LogMaster.INSTANCE.log("GameStart::init");
        IWorldChain world = VarField.worldChain;
        Player player = Player.PLAYER;
        if (player.overlaps(player.coordinate.x, player.coordinate.y)) {
            player.ghost = true;
        }
    }
}
