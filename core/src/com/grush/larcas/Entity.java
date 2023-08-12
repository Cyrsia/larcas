package com.grush.larcas;

import java.util.Map;

public abstract class Entity implements Renderable {
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
    boolean spawned;
    boolean entityCollision = true;
    boolean groupCollision = true;
    IWorldChain worldChain;
    public int hp;
    public Entity(Coordinate<Float> coordinate, Map<?, ?> states, IWorldChain worldChain){
        this.coordinate = coordinate;
        this.states = states;
        this.worldChain = worldChain;
    }
    public void spawn(){
        prevX = coordinate.x;
        prevY = coordinate.y;
        EntityManager.INSTANCE.addEntity(this);
    }
    public boolean isFloating(){
        if (floating) return true;
        return isGhost();
    }
    public boolean overlapsRaw(float xn, float yn){ //не использует ghost-проверку
        for (int y = (int)yn - 1; y < (int)(yn + this.size[1] + 1); y++){
            for (int x = (int)xn - 1; x < (int)(xn + this.size[0] + 1); x++){
                if (worldChain.getBlock(x, y).collides(this.coordinate, size)){
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
            this.collision();
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
            this.collision();
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
    public void collision(){}

    public void update() {
        updateXAxis();
        updateYAxis();
        checkHp();
    }

    public void kill(){
        EntityManager.INSTANCE.removeEntity(this);
    }

    public double[] getCheckDistance(float[] entitySize){
        double[] res = new double[2];
        res[0] = Math.max(
                    Math.sqrt(size[0]*size[0] + size[1]*size[1]),
                    Math.sqrt(entitySize[0]*entitySize[0] + entitySize[1]*entitySize[1])
        );
        res[1] = Math.max(
                    Math.min(size[0], size[1]),
                    Math.min(entitySize[0], entitySize[1])
                );
        return res;
    }

    public boolean entityOverlap(Coordinate<Float> entityCoordinate, float[] eSize){
        float x1 = entityCoordinate.x;
        float y1 = entityCoordinate.y;
        return (x1 < (this.coordinate.x + this.size[0])) & ((x1 + eSize[0]) > this.coordinate.x) & (y1 < (this.coordinate.y + this.size[1])) & ((y1 + eSize[1]) > this.coordinate.y);
    }

    public void entityCollisionCheck(){
        if (entityCollision){
            for (Entity entity : EntityManager.INSTANCE.entities){
                if (entity == this || entity.getClass() == this.getClass()){
                    continue;
                }
                double[] checkDistance = getCheckDistance(entity.size);
                float distance = GameLogic.getDistance(entity.coordinate, this.coordinate);
                if (distance <= checkDistance[0]){
                    if (distance <= checkDistance[1]){
                        this.entityCollision(entity);
                    } else if (entityOverlap(entity.coordinate, entity.size)){
                        this.entityCollision(entity);
                    }
                }
            }
        }
    }

    public void entityCollision(Entity entity){
        entity.collision();
    }

    public void reduceHp(Damage damage){
        this.hp-=damage.damage;
    }

    public void checkHp(){
        if (hp <= 0){
            this.kill();
        }
    }
}