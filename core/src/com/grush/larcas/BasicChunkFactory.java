package com.grush.larcas;

public class BasicChunkFactory implements IChunkFactory {
    @Override
    public Chunk generateChunk(int x, int y) {
        LogMaster.INSTANCE.log("generateChunk");
        Chunk chunk = new Chunk(x, y);
        for (int y2 = 0; y2 < Chunk.sizeY; y2++) {
            for (int x2 = 0; x2 < Chunk.sizeX; x2++) {
                chunk.setBlock(x2, y2, new BreakableBlock(x*Chunk.sizeX+x2, y*Chunk.sizeY+y2));
            }
        }
        return chunk;
    }
}
