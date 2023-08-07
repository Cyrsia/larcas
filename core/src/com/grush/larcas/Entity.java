package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

abstract class Entity {
    Coordinate<Float> coordinate;
    Map<?, ?> states;
    boolean visible = true;
    float interactionRange = 4f;

    private static final float MOVEMENT_SPEED = 0.1f;

    public Entity(Coordinate<Float> coordinate, Map<?, ?> states){
        this.coordinate = coordinate;
        this.states = states;
        EntityManager.INSTANCE.addEntity(this);
    }

    public void move(float dx, float dy) {
        if (!isCollision(dx, dy)){
            coordinate.x += dx * MOVEMENT_SPEED;
            coordinate.y += dy * MOVEMENT_SPEED;
        }
    }

    public Texture getTexture(){
        return null;
    }

    public boolean isCollision(float dx, float dy) {
        float newX = coordinate.x + dx * MOVEMENT_SPEED;
        float newY = coordinate.y + dy * MOVEMENT_SPEED;

        for (int y = (int) newY; y <= newY + 1; y++) {
            for (int x = (int) newX; x <= newX + 1; x++) {
                Block block = World.INSTANCE.getBlock(x, y);
                if (block.isSolid && GameLogic.doOverlap(coordinate, new Coordinate<>(x, y))) {
                    return true;
                }
            }
        }

        return false;
    }
}