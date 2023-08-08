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
            for (int y = 0; y < size[1]; y++) {
                float chance = 0.01f;
                for (int xx = -1; xx < 2; xx++) {
                    for (int yy = -1; yy < 2; yy++) {
                        if (world.getBlock(x + xx, y + yy).isSolid) {
                            chance += 0.23f;
                        }
                    }
                }
                if ((float) random.nextInt(100) /100 < chance) {
                    world.setBlock(x, y, BreakableBlock.class);
                }
            }
        }

        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                float chance = 0f;
                for (int xx = -1; xx < 2; xx++) {
                    for (int yy = -1; yy < 2; yy++) {
                        Class<? extends Block> block = world.getBlock(x + xx, y + yy).getClass();
                        if (block == BreakableBlock.class) {
                            chance += 1f;
                        }
                    }
                }
                if (chance >= 7) {
                    world.setBlock(x, y, StoneBlock.class);
                }
            }
        }
    }
}
