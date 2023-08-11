package com.grush.larcas;

public class BasicChunkFactory implements IChunkFactory {
    @Override
    public Chunk generateChunk(int x, int y) {
        LogMaster.INSTANCE.log("generateChunk");
        return new Chunk(x, y);
    }
}
