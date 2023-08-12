package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
    final public static Player PLAYER = new Player(new Coordinate<>((float) World.INSTANCE.getSize()[0]/2, (float) World.INSTANCE.getSize()[1]/2), new HashMap<>());
    float speed = 0.15f;
    Camera camera;

    private Player(Coordinate<Float> coordinate, Map<?, ?> states) {
        super(coordinate, states);
        this.size = new float[]{0.75f, 0.75f};
        camera = Camera.INSTANCE;
        this.hp = 1000;
        this.spawn();
    }

    public void interact() {
        if (GameLogic.getDistance(coordinate, Camera.INSTANCE.blockCoordinate) < interactionRange) {
            Block blockToInteract = World.INSTANCE.getBlock(camera.blockX, camera.blockY);
            blockToInteract.interact();
        }

    }
    public void hit() {
        if (GameLogic.getDistance(coordinate, camera.blockCoordinate) < interactionRange) {
            Block blockToHit = World.INSTANCE.getBlock(camera.blockX, camera.blockY);
            blockToHit.hit();
        }
    }
    public void spell(){
        Entity bullet = new Bullet(null, null);
        float distance = GameLogic.getDistance(this.coordinate, camera.blockCoordinate);
        float axisX = (camera.blockCoordinate.x - this.coordinate.x) / distance;
        float axisY = (camera.blockCoordinate.y - this.coordinate.y) / distance;
        bullet.coordinate = new Coordinate<>(this.coordinate.x + axisX*2, this.coordinate.y +axisY*2);
        bullet.vector = new MoveVector(axisX*10, axisY*10, 10f);
        bullet.spawn();
    }

    public void spell(int ID){
        if (ID == 1){
            new Dummy(camera.blockCoordinate.clone(), null).spawn();
        }
    }
    @Override
    public Texture getTexture() {
        return TexMaster.INSTANCE.TestTex;
    }
    @Override
    public void reduceHp(Damage damage){
        LogMaster.INSTANCE.log("reduceHp (Player)");
    }
}
