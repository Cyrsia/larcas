package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
    final public static Player PLAYER = new Player(new Coordinate<Float>(0f, 6f), new HashMap<>());
    private static final float MOVEMENT_SPEED = 0.1f;
    Camera camera;

    private Player(Coordinate<Float> coordinate, Map<?, ?> states) {
        super(coordinate, states);
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
