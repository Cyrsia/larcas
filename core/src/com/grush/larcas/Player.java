package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
    final public static Player PLAYER = new Player(new Coordinate<Float>((float) World.INSTANCE.getSize()[0]/2, (float) World.INSTANCE.getSize()[1]/2), new HashMap<>());
    private static final float MOVEMENT_SPEED = 0.1f;
    float speed = MOVEMENT_SPEED;
    Camera camera;

    private Player(Coordinate<Float> coordinate, Map<?, ?> states) {
        super(coordinate, states);
        this.size = new float[]{0.75f, 0.75f};
        camera = Camera.INSTANCE;

    }

    public void interact() {
        if (GameLogic.getDistance(coordinate, Camera.INSTANCE.blockCoordinate) < interactionRange) {
            Block blockToInteract = World.INSTANCE.getBlock(camera.blockX, camera.blockY);
            blockToInteract.interact();
        }

    }
    public void hit() {
        if (GameLogic.getDistance(coordinate, Camera.INSTANCE.blockCoordinate) < interactionRange) {
            Block blockToHit = World.INSTANCE.getBlock(camera.blockX, camera.blockY);
            blockToHit.hit();
        }
    }
    @Override
    public Texture getTexture() {
        return TexMaster.INSTANCE.TestTex;
    }
}
