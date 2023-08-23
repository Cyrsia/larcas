package com.grush.larcas;

import java.util.HashMap;
import java.util.Map;

public abstract class Block implements Renderable {
    int x;
    int y;
    public Map<String, String> states = new HashMap<>();
    boolean visible = true;
    boolean isSolid = true;
    IWorldChain worldChain;
    public static float[] size = new float[]{1f, 1f};
    public boolean overlap(Coordinate<Float> coordinate, float w1, float h1){
        float x1 = coordinate.x;
        float y1 = coordinate.y;
        return (x1 < (this.x + size[0])) & ((x1 + w1) > this.x) & (y1 < (this.y + size[1])) & ((y1 + h1) > this.y);
    }
    public boolean collides(Coordinate<Float> coordinate, float[] size){
        if (!isSolid){
            return false;
        }
        return overlap(coordinate, size[0], size[1]);
    }

    Block (int x, int y, IWorldChain worldChain){
        this.x = x;
        this.y = y;
        this.worldChain = worldChain;
    }

    public void hit(){
    }

    public void updateState(){
    }
    public void spawn(){
        worldChain.getBlock(this.x, this.y-1).updateState();
        worldChain.getBlock(this.x, this.y+1).updateState();
    }
    public void despawn(){
        worldChain.setBlock(this.x, this.y, VoidBlock.class);
    }
    public void destroy(){
        this.despawn();
    }
    public void interact(){
    }
}
