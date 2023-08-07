package com.grush.larcas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameLogic {
    public static GameLogic INSTANCE = new GameLogic();
	World world;
	Coordinate<Integer> cameraPosition;
	int blockSize;
	int blockX;
	int blockY;

    private GameLogic(){
		world = World.INSTANCE;
		cameraPosition = Camera.INSTANCE.coordinate;
		GameStart.INSTANCE.start();

	}

    public void update(){
		blockSize = Camera.INSTANCE.blockSize;
        blockX = (Gdx.input.getX() + cameraPosition.x) / blockSize;
        blockY = (Gdx.graphics.getHeight() - Gdx.input.getY() + cameraPosition.y) / blockSize;

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			World.INSTANCE.getBlock(blockX, blockY).hit();
		}

		Camera.INSTANCE.update();
    }
}
