package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

abstract class Entity {
    Coordinate<Float> coordinate;
    Map<?, ?> states;
    boolean visible = true;
    float interactionRange = 4f;
    private static final float MOVEMENT_SPEED = 0.1f;
    public MoveVector vector = new MoveVector(0,0,3f);
    float deceleration = 0.01f;
    float[] size;
    public Entity(Coordinate<Float> coordinate, Map<?, ?> states){
        this.coordinate = coordinate;
        this.states = states;
        EntityManager.INSTANCE.addEntity(this);
    }
    public Texture getTexture(){
        return null;
    }
    public boolean overlaps(float xn, float yn){
        for (int y = (int)yn - 1; y < (int)(yn + this.size[1] + 1); y++){
            for (int x = (int)xn - 1; x < (int)(xn + this.size[0] + 1); x++){
                if (World.INSTANCE.getBlock(x, y).collides(this.coordinate, size)){
                    return true;
                }
            }
        }
        return false;
    }
    float prevX, prevY;
    public void updateXAxis() {
        vector.decelerateAccelerationX(deceleration);
        coordinate.x += vector.dx * MOVEMENT_SPEED;

        if (overlaps(coordinate.x, coordinate.y)) {
            while (overlaps(coordinate.x, coordinate.y)) {
                coordinate.x = prevX;
                vector.decelerateAccelerationX(deceleration);
                coordinate.x += vector.dx * MOVEMENT_SPEED;
            }
        } else {
            prevX = coordinate.x;
        }
    }

    public void updateYAxis() {
        vector.decelerateAccelerationY(deceleration);
        coordinate.y += vector.dy * MOVEMENT_SPEED;

        if (overlaps(coordinate.x, coordinate.y)) {
            while (overlaps(coordinate.x, coordinate.y)) {
                coordinate.y = prevY;
                vector.decelerateAccelerationY(deceleration);
                coordinate.y += vector.dy * MOVEMENT_SPEED;
            }
        } else {
            prevY = coordinate.y;
        }
    }

    public void update() {
        updateXAxis();
        updateYAxis();
    }

}