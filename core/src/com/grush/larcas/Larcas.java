package com.grush.larcas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Larcas extends ApplicationAdapter {
	SpriteBatch batch;
	IWorldChain world;
	Coordinate<Float> cameraPosition;

	@Override
	public void create () {
		LogMaster.INSTANCE.log("create");
//		LogMaster.INSTANCE.log(World.INSTANCE_);

		batch = new SpriteBatch();

		world = VarField.worldChain;

		while (world.getSize() == null){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				LogMaster.INSTANCE.error(e);
			}
		}

		LogMaster.INSTANCE.log(GameLogic.INSTANCE);

		VarField.actionHost.spawn(Player.PLAYER);

		cameraPosition = Camera.INSTANCE.coordinate;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		batch.begin();

		GameLogic.INSTANCE.update();

		Camera.INSTANCE.update();
		WorldRender();
		EntityRender();

		batch.end();
	}
	
	@Override
	public void dispose () {
		LogMaster.INSTANCE.log("dispose");
		TexMaster.INSTANCE.disposeAll();
		world.dispose();
		batch.dispose();
	}
	public void EntityRender() {
		Camera.INSTANCE.updateP(Player.PLAYER.coordinate.x, Player.PLAYER.coordinate.y, Player.PLAYER.size);
		for (Entity entity : world.getEntityManager().entities) {
			if (entity.visible & GameLogic.getDistance(Player.PLAYER.coordinate, entity.coordinate) <= Camera.INSTANCE.renderDistance) {
				entity.draw(batch,
						entity.coordinate.x*Camera.INSTANCE.blockSize - cameraPosition.x,
						entity.coordinate.y*Camera.INSTANCE.blockSize - cameraPosition.y,
						Camera.INSTANCE.blockSize*entity.size[0],
						Camera.INSTANCE.blockSize*entity.size[1]);
			}
		}
	}

	public void WorldRender() {
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
                            block.draw(batch,
									((float)(x * Chunk.sizeX + chunkX)) * Camera.INSTANCE.blockSize - cameraPosition.x,
                                    ((float)(y * Chunk.sizeY + chunkY)) * Camera.INSTANCE.blockSize - cameraPosition.y,
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
