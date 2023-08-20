package com.grush.larcas;

public interface IWorldChain {
    World getWorld();

    Block getBlock(int x, int y);
    Block setBlock(int x, int y, Class<? extends Block> blockType);

    int[] getSize();
    int getChunkX(int x);
    int getChunkY(int y);
    Chunk getChunk(int x, int y);
    void dispose();
}

