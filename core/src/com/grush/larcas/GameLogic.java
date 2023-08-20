package com.grush.larcas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameLogic {
    public static GameLogic INSTANCE = new GameLogic();
	IWorldChain world;
	Coordinate<Float> cameraPosition;
	float blockSize;
	int blockX;
	int blockY;
	Player player;

    private GameLogic(){
		LogMaster.INSTANCE.log("GameLogic");
		world = VarField.worldChain;
		cameraPosition = Camera.INSTANCE.coordinate;
		GameStart.INSTANCE.start();
		player = Player.PLAYER;
	}

	public static float getDistance(Coordinate<Float> a, Coordinate<Float> b) {
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
		if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
			player.spell();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
			player.spell(1);
		}
    }
}
