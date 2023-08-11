package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
    final public static Player PLAYER = new Player(new Coordinate<Float>((float) World.INSTANCE.getSize()[0]/2, (float) World.INSTANCE.getSize()[1]/2), new HashMap<>());
    float speed = 0.15f;
    Camera camera;

    private Player(Coordinate<Float> coordinate, Map<?, ?> states) {
        super(coordinate, states);
        this.size = new float[]{0.75f, 0.75f};
        camera = Camera.INSTANCE;
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
        Entity bullet = new Bullet(new Coordinate<>(coordinate.x, coordinate.y), null);
        float distance = GameLogic.getDistance(bullet.coordinate, camera.blockCoordinate);
        float axisX = (camera.blockCoordinate.x - bullet.coordinate.x) / distance;
        float axisY = (camera.blockCoordinate.y - bullet.coordinate.y) / distance;
        bullet.vector = new MoveVector(axisX*10, axisY*10, 10f);
        bullet.spawn();
    }
    @Override
    public Texture getTexture() {
        return TexMaster.INSTANCE.TestTex;
    }
}
