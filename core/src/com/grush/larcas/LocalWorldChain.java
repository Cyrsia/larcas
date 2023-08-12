package com.grush.larcas;

public class LocalWorldChain implements IWorldChain {
    private World world;
    public LocalWorldChain(World world) {
        this.world = world;
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
}
