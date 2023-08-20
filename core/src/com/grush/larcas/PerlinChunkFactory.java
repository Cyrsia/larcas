package com.grush.larcas;

public class PerlinChunkFactory implements IChunkFactory {
    PerlinNoiseGenerator noiseGenerator;
    public PerlinChunkFactory(){
        LogMaster.INSTANCE.log("PerlinChunkFactory");
        noiseGenerator = new PerlinNoiseGenerator(Chunk.sizeX, Chunk.sizeY, 71);
    }

    @Override
    public Chunk generateChunk(int x, int y, IWorldChain worldChain) {
        Chunk chunk = new Chunk(x, y, worldChain);
        float[][] map = noiseGenerator.generateNoiseMap(x, y);
        for (int y2 = 0; y2 < Chunk.sizeY; y2++) {
            for (int x2 = 0; x2 < Chunk.sizeX; x2++) {
                if (map[y2][x2] >= 0.1){
                    chunk.setBlock(x2, y2, new BreakableBlock(x*Chunk.sizeX+x2, y*Chunk.sizeY+y2, worldChain));
                }
            }
        }
        return chunk;
    }
}
