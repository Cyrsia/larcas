package com.grush.larcas;

public class World {
    static final public int sizeX = 50;
    static final public int sizeY = 50;
    public static float gravity = 0.01f;

    private final Chunk[][] data = new Chunk[sizeY][sizeX];

    public static World INSTANCE = new World();

    public int[] getSize(){
        return new int[]{sizeX*Chunk.sizeX, sizeY*Chunk.sizeY};
    }

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
        } else {
            return new VoidBlock(x, y);
        }
    }
    public Block setBlock(int x, int y, Class<? extends Block> blockType) {
        if (isValidPosition(x, y)) {
            try {
                Block newBlock = blockType.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
                Chunk chunk = this.getChunk(x, y);
                chunk.setBlock(x % Chunk.sizeX, y % Chunk.sizeY, newBlock);
                newBlock.spawn();
                return newBlock;
            } catch (Exception e) {
                LogMaster.INSTANCE.log("setBlock exception: " + e.getMessage());
            }
        }
        return null;
    }
}
