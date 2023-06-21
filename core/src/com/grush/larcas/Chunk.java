package com.grush.larcas;

public class Chunk {
    final int x;
    final int y;
    static final public int sizeX = 5;
    static final public int sizeY = 5;
    private Block[][] data;
    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;
        this.data = new Block[sizeY][sizeX];

        for (int blockY = 0; blockY < sizeY; blockY++){
            for (int blockX = 0; blockX < sizeX; blockX++) {
                if ((x + y) % 2 == 0) this.data[blockY][blockX] = new StoneBlock();
            }
        }
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
    public Block getBlock(int x, int y){
        if (this.isValidCoordinates(x,y)){
            return this.data[y][x];
        } else {
            LogMaster.INSTANCE.log("getBlock null");
            return null;
        }
    }
    public boolean isValidCoordinates(int x, int y) {
        boolean res = x >= 0 && x < Chunk.sizeX && y >= 0 && y < Chunk.sizeY;
        if (!res) LogMaster.INSTANCE.log("isValidCoordinates false");
        return res;
    }
}
