package com.grush.larcas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Larcas extends ApplicationAdapter {
	SpriteBatch batch;
	World world;
	Coordinate<Float> cameraPosition;

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

		WorldRend();
		GameLogic.INSTANCE.update();
		EntityRender();

		Camera.INSTANCE.update();

		batch.end();
	}
	
	@Override
	public void dispose () {
		LogMaster.INSTANCE.log("dispose");
		TexMaster.INSTANCE.disposeAll();
		batch.dispose();
	}
	public void EntityRender() {
		for (Entity entity : EntityManager.INSTANCE.entities) {
			if (entity.visible) {
				batch.draw(
					entity.getTexture(),
					entity.coordinate.x*Camera.INSTANCE.blockSize - cameraPosition.x,
					entity.coordinate.y*Camera.INSTANCE.blockSize - cameraPosition.y,
					Camera.INSTANCE.blockSize*entity.size[0],
					Camera.INSTANCE.blockSize*entity.size[1]
				);
			}
		}
	}

	public void WorldRend() {
		int minXChunk = Math.max(0, world.getChunkX(Player.PLAYER.coordinate.x.intValue() - Camera.INSTANCE.renderDistance));
		int maxXChunk = Math.min(World.sizeX, world.getChunkX(Player.PLAYER.coordinate.x.intValue() + Camera.INSTANCE.renderDistance));
		int minYChunk = Math.max(0, world.getChunkY(Player.PLAYER.coordinate.y.intValue() - Camera.INSTANCE.renderDistance));
		int maxYChunk = Math.min(World.sizeY, world.getChunkY(Player.PLAYER.coordinate.y.intValue() + Camera.INSTANCE.renderDistance));

        for (int y = minYChunk; y < maxYChunk; y++) {
            for (int x = minXChunk; x < maxXChunk; x++) {
				Block[][] chunk = world.getChunk(x, y).getData();
                for (int chunkY = 0; chunkY < Chunk.sizeY; chunkY++) {
                    for (int chunkX = 0; chunkX < Chunk.sizeX; chunkX++) {
                        Block block = chunk[chunkY][chunkX];
                        if (block != null && block.visible) {
                            batch.draw(
                                    block.getTexture(),
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
