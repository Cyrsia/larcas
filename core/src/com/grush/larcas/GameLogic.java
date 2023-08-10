package com.grush.larcas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.math.Rectangle;

import java.util.Objects;

public class GameLogic {
    public static GameLogic INSTANCE = new GameLogic();
	World world;
	Coordinate<Float> cameraPosition;
	float blockSize;
	int blockX;
	int blockY;
	Player player;

    private GameLogic(){
		world = World.INSTANCE;
		cameraPosition = Camera.INSTANCE.coordinate;
		GameStart.INSTANCE.start();
		player = Player.PLAYER;
	}

	public static float getDistance(Coordinate<Float> a, Coordinate<Integer> b) {
		float dx = a.x - b.x;
		float dy = a.y - b.y;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}


    public void update(){
		blockSize = Camera.INSTANCE.blockSize;

		blockX = (int) ((Gdx.input.getX() + cameraPosition.x) / blockSize); // Высчитываются координаты мышки по мерке мира игры
        blockY = (int) ((Gdx.graphics.getHeight() - Gdx.input.getY() + cameraPosition.y) / blockSize);

		EntityManager.INSTANCE.updateAll();

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			player.hit();
		} else if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
			player.interact();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.vector.addX(player.speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.vector.addX(-player.speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.vector.addY(player.speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.vector.addY(-player.speed);
		}

		Camera.INSTANCE.update();
    }
}
