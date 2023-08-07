package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

abstract class Block {
    final static Block nullBlock = null;
    int x;
    int y;

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
}
