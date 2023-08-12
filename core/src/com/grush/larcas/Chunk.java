package com.grush.larcas;

public class Chunk {
    final int x;
    final int y;
    static final public int sizeX = 10;
    static final public int sizeY = 10;
    private final Block[][] data;
    public IWorldChain worldChain;
    public Chunk(int x, int y, IWorldChain worldChain){
        this.worldChain = worldChain;
        this.x = x;
        this.y = y;
        this.data = new Block[sizeY][sizeX];
    }
    public Block[][] getData(){
        return this.data;
    }
    public int setBlock(int x, int y, Block block){
        if (this.isValidCoordinates(x, y)){
            this.data[y][x] = block;
            return 0;
        } else {
            LogMaster.INSTANCE.log("setBlock null");
            return -1;
        }
    }
    private Block block(int x, int y){
        Block block = this.data[y][x];
        if (block != null){
            return block;
        } else {
            return new VoidBlock(this.x*sizeX + x, this.y*sizeY + y, worldChain);
        }
    }
    public Block getBlock(int x, int y){
        if (this.isValidCoordinates(x,y)){
            return block(x, y);
        } else {
            LogMaster.INSTANCE.log("getBlock(Chunk) null");
            return null;
        }
    }
    public boolean isValidCoordinates(int x, int y) {
        boolean res = x >= 0 && x < Chunk.sizeX && y >= 0 && y < Chunk.sizeY;
        if (!res) {
            LogMaster.INSTANCE.log("isValidCoordinates false");
            throw new IllegalArgumentException();
        }
        return res;
    }
}
