package com.grush.larcas;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocalWorldChain implements IWorldChain {
    private final World world;
    private ScheduledExecutorService executorService;
    public LocalWorldChain(World world) {
        this.world = world;
        initializeUpdater();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Block getBlock(int x, int y) {
        return world.getBlock(x, y);
    }

    @Override
    public Block setBlock(int x, int y, Class<? extends Block> blockType) {
        return world.setBlock(x, y, blockType);
    }

    @Override
    public int[] getSize() {
        return world.getSize();
    }

    @Override
    public int getChunkX(int x) {
        return world.getChunkX(x);
    }

    @Override
    public int getChunkY(int y) {
        return world.getChunkY(y);
    }

    @Override
    public Chunk getChunk(int x, int y) {
        return world.getChunk(x, y);
    }

    private void initializeUpdater() {
        executorService = Executors.newScheduledThreadPool(2);

        long periodMillis = 1000 / 60;
        executorService.scheduleAtFixedRate(this::updateGameLogic, 0, periodMillis, TimeUnit.MILLISECONDS);
    }

    private void updateGameLogic() {
        GameLogic.INSTANCE.update();
    }
    public void dispose() {
        if (!executorService.isShutdown()){
            executorService.shutdown();
        }
    }
}
