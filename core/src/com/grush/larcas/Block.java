package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

abstract class Block {
    int x;
    int y;
    public Map<String, String> states = new HashMap<>();
    boolean visible = true;

    Block (int x, int y){
        this.x = x;
        this.y = y;
    }
    public Texture getTexture(){
        return null;
    }

    public int getUniqueState(int range){
        return this.hashCode() % range;
    }

    public void hit(){
    }

    public void updateState(){
    }
    public void spawn(){
        World.INSTANCE.getBlock(this.x, this.y-1).updateState();
        World.INSTANCE.getBlock(this.x, this.y+1).updateState();
    }
    public void despawn(){
        World.INSTANCE.setBlock(this.x, this.y, VoidBlock.class);
    }
    public void destroy(){
        this.despawn();
    }
}
