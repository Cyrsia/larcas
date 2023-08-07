package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

abstract class Entity {
    Coordinate<Float> coordinate;
    Map<?, ?> states;
    boolean visible = true;
    float interactionRange = 4f;


    public Entity(Coordinate<Float> coordinate, Map<?, ?> states){
        this.coordinate = coordinate;
        this.states = states;
        EntityManager.INSTANCE.addEntity(this);
    }

    public abstract void move(float dx, float dy);

    public Texture getTexture(){
        return null;
    }
}
