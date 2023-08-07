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
	int blockSize;
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

	static boolean doOverlap(Coordinate<Float> l1, Coordinate<Integer> l2) {
		Rectangle rect1 = new Rectangle(l1.x, l1.y, 0.1f,0.1f);
		Rectangle rect2 = new Rectangle(l2.x, l2.y, 0.1f,0.1f);

		return !rect1.overlaps(rect2);
	}


    public void update(){
		blockSize = Camera.INSTANCE.blockSize;

		blockX = (int) ((Gdx.input.getX() + cameraPosition.x) / blockSize); // Высчитываются координаты мышки по мерке мира игры
        blockY = (int) ((Gdx.graphics.getHeight() - Gdx.input.getY() + cameraPosition.y) / blockSize);

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			player.hit();
		} else if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
			player.interact();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.move(0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.move(0, -1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.move(1, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.move(-1, 0);
		}

		Camera.INSTANCE.update();
    }
}
