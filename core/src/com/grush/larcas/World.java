package com.grush.larcas;

public class World {
    static final public int sizeX = 6;
    static final public int sizeY = 4;

    private Chunk[][] data = new Chunk[sizeY][sizeX];

    public static World INSTANCE = new World();

    private World(){
        for (int y = 0; y<sizeY; y++){
            for (int x = 0; x<sizeX; x++){
                this.data[y][x] = new Chunk(x, y);
            }
        }
        LogMaster.INSTANCE.log("new World");
    }
    public Chunk[][] getData(){
        return this.data;
    }
    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < sizeX*Chunk.sizeX) && (y >= 0 && y < sizeY*Chunk.sizeY);
    }
    public int getChunkX(int x) {
        return x / Chunk.sizeX;
    }
    public int getChunkY(int y) {
        return y / Chunk.sizeY;
    }
    public Chunk getChunk(int x, int y){
        return data[getChunkY(y)][getChunkX(x)];
    }
    public Block getBlock(int x, int y){
        if (isValidPosition(x, y)){
            Chunk chunk = this.getChunk(x, y);
            return chunk.getBlock(x%Chunk.sizeX, y%Chunk.sizeY);
        }
        return null;
    }
    public int setBlock(int x, int y, Block newBlock){
        if (isValidPosition(x, y)){
            Chunk chunk = this.getChunk(x, y);
            chunk.setBlock(x%Chunk.sizeX, y%Chunk.sizeY, newBlock);
            return 1;
        }
        return -1;
    }
}
