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

    Texture texture;
    public Texture getTexture(){
        return texture;
    }

    public int getUniqueState(int range){
        return this.hashCode() % range;
    }

    public void hit(){
    }
}
