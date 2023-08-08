package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;
import jdk.jpackage.internal.Log;

import java.util.Map;

abstract class Entity {
    Coordinate<Float> coordinate;
    Map<?, ?> states;
    boolean visible = true;
    float interactionRange = 4f;
    private static final float MOVEMENT_SPEED = 0.1f;
    public MoveVector vector = new MoveVector(0,0,1.5f);
    float deceleration = 0.005f;


    public Entity(Coordinate<Float> coordinate, Map<?, ?> states){
        this.coordinate = coordinate;
        this.states = states;
        this.vector.lockY = false;
        EntityManager.INSTANCE.addEntity(this);
    }
    public Texture getTexture(){
        return null;
    }
    float newX, newY;
    public void update(){
        vector.decelerateAccelerationX(deceleration);
        vector.decelerateAccelerationY(deceleration);

        newX = coordinate.x + vector.dx * MOVEMENT_SPEED;
        newY = coordinate.y + vector.dy * MOVEMENT_SPEED;

        coordinate.x = newX;
        coordinate.y = newY;
    };
}