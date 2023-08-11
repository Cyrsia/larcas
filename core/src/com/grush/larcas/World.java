package com.grush.larcas;

public class World {
    static final public int sizeX = 200;
    static final public int sizeY = 200;

    private final Chunk[][] data = new Chunk[sizeY][sizeX];

    public static World INSTANCE = new World();

    IChunkFactory factory = new BasicChunkFactory();

    public int[] getSize(){
        return new int[]{sizeX*Chunk.sizeX, sizeY*Chunk.sizeY};
    }

    private World(){
        LogMaster.INSTANCE.log("new World");
    }
    public Chunk[][] getData(){
        return this.data;
    }
    public Chunk getChunk(int x, int y){
        Chunk chunk = this.data[y][x];
        if (chunk == null){
            this.data[y][x] = factory.generateChunk(x, y);
        }
        return this.data[y][x];
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
    public Chunk getChunkByBlock(int x, int y){
        return data[getChunkY(y)][getChunkX(x)];
    }
    public Block getBlock(int x, int y){
        if (isValidPosition(x, y)){
            Chunk chunk = this.getChunkByBlock(x, y);
            return chunk.getBlock(x%Chunk.sizeX, y%Chunk.sizeY);
        } else {
            return new VoidBlock(x, y);
        }
    }
    public Block setBlock(int x, int y, Class<? extends Block> blockType) {
        if (isValidPosition(x, y)) {
            try {
                Block newBlock = blockType.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
                Chunk chunk = this.getChunkByBlock(x, y);
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
