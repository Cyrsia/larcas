package com.grush.larcas.server;

import com.grush.larcas.Block;
import com.grush.larcas.Chunk;
import com.grush.larcas.EntityManager;
import com.grush.larcas.IWorldChain;

public class ServerWorldChain implements IWorldChain {

    @Override
    public Block getBlock(int x, int y) {
        return null;
    }

    @Override
    public Block setBlock(int x, int y, Class<? extends Block> blockType) {
        return null;
    }

    @Override
    public int[] getSize() {
        return new int[0];
    }

    @Override
    public int getChunkX(int x) {
        return 0;
    }

    @Override
    public int getChunkY(int y) {
        return 0;
    }

    @Override
    public Chunk getChunk(int x, int y) {
        return null;
    }

    @Override
    public void dispose() {

    }
    @Override
    public EntityManager getEntityManager() {
        return null;
    }
}
