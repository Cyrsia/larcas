package com.grush.larcas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Larcas extends ApplicationAdapter {
	SpriteBatch batch;
	World world;
	Coordinate<Integer> cameraPosition;

	@Override
	public void create () {
		LogMaster.INSTANCE.log("create");
		batch = new SpriteBatch();

		world = World.INSTANCE;
		cameraPosition = Camera.INSTANCE.coordinate;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		batch.begin();

		GameLogic.INSTANCE.update();
		WorldRend();

		batch.end();
	}
	
	@Override
	public void dispose () {
		LogMaster.INSTANCE.log("dispose");
		TexMaster.INSTANCE.disposeAll();
		batch.dispose();
	}
    Block tempBlock;
	public void WorldRend() {
		for (int y = 0; y < World.sizeY; y++) {
			for (int x = 0; x < World.sizeX; x++) {
				for (int chunkY = 0; chunkY < Chunk.sizeY; chunkY++) {
					for (int chunkX = 0; chunkX < Chunk.sizeX; chunkX++) {
						if (world.getData()[y][x].getData()[chunkY][chunkX] != null) {
                            tempBlock = world.getData()[y][x].getData()[chunkY][chunkX];
                            if (tempBlock.visible){
                                batch.draw(
                                        world.getData()[y][x].getData()[chunkY][chunkX].getTexture(),
                                        (x * Chunk.sizeX + chunkX) * Camera.INSTANCE.blockSize - cameraPosition.x,
                                        (y * Chunk.sizeY + chunkY) * Camera.INSTANCE.blockSize - cameraPosition.y,
                                        Camera.INSTANCE.blockSize,
                                        Camera.INSTANCE.blockSize
                                );
                            }
						}
					}
				}
			}
		}
	}
}
