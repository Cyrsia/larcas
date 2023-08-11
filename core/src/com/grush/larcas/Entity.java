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
    boolean ghost = false;
    boolean forcedGhost = false;
    float restitution = 0.2f;
    boolean floating = false;
    public Entity(Coordinate<Float> coordinate, Map<?, ?> states){
        this.coordinate = coordinate;
        this.states = states;
        prevX = coordinate.x;
        prevY = coordinate.y;
    }
    public void spawn(){
        EntityManager.INSTANCE.addEntity(this);
    }
    public boolean isFloating(){
        if (floating) return true;
        return isGhost();
    }
    public Texture getTexture(){
        return null;
    }
    public boolean overlapsRaw(float xn, float yn){ //не использует ghost-проверку
        for (int y = (int)yn - 1; y < (int)(yn + this.size[1] + 1); y++){
            for (int x = (int)xn - 1; x < (int)(xn + this.size[0] + 1); x++){
                if (World.INSTANCE.getBlock(x, y).collides(this.coordinate, size)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean overlaps(float xn, float yn){
        boolean res = overlapsRaw(xn, yn);
        if (isGhost()){
            if (!ghost){
                if (!res){
                    this.forcedGhost = false;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return res;
    }

    public
    float prevX, prevY;
    public void updateXAxis() {
        vector.decelerateAccelerationX(deceleration);
        coordinate.x += vector.dx * MOVEMENT_SPEED;
        int counter = 0;

        if (overlaps(coordinate.x, coordinate.y)) {
            while (overlaps(coordinate.x, coordinate.y)) {
                counter++;
                if (counter > 500) this.forcedGhost = true;
                coordinate.x = prevX;
                vector.decelerateAccelerationX(deceleration);
                vector.dx = (-vector.dx)*restitution;

                coordinate.x += vector.dx * MOVEMENT_SPEED;
            }
        } else {
            prevX = coordinate.x;
        }
    }

    public boolean isGhost(){
        if (ghost) return true;
        return forcedGhost;
    }


    public void updateYAxis() {
        if (isFloating()) {
            vector.decelerateAccelerationY(deceleration);
        } else {
            vector.addY(-World.gravity);
        }
        coordinate.y += vector.dy * MOVEMENT_SPEED;
        int counter = 0;

        if (overlaps(coordinate.x, coordinate.y)) {
            while (overlaps(coordinate.x, coordinate.y)) {
                counter++;
                if (counter > 500) this.forcedGhost = true;
                coordinate.y = prevY;
                vector.decelerateAccelerationY(deceleration);

                vector.dy = (-vector.dy)*restitution;
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