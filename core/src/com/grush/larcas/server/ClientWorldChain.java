package com.grush.larcas.server;

import com.grush.larcas.*;

import java.util.Arrays;
import java.util.Map;

public class ClientWorldChain implements IWorldChain {
    String host;
    int port;
    int[] size;
    ServerHandler serverHandler;
    Map<String, Chunk> chunks;
    EntityManager entityManager;
    public ClientWorldChain(String host, int port) {
        this.host = host;
        this.port = port;
        LogMaster.INSTANCE.log("ClientWorldChain");
        serverHandler = new ServerHandler(host, port, this);
        serverHandler.start();
        chunks = new java.util.HashMap<>();
        this.entityManager = new EntityManager();
    }
    @Override
    public Block getBlock(int x, int y) {
        int[] chunk = new int[]{getChunkX(x), getChunkY(y)};
        if (!chunks.containsKey(Arrays.toString(chunk))){
            chunks.put(Arrays.toString(chunk), getChunk(chunk[0], chunk[1]));
        }
        return chunks.get(Arrays.toString(chunk)).getBlock(x%Chunk.sizeX, y%Chunk.sizeY);
    }

    @Override
    public Block setBlock(int x, int y, Class<? extends Block> blockType) {
        return null;
    }

    @Override
    public int[] getSize() {
        return size;
    }

    @Override
    public int getChunkX(int x) {
        return x / Chunk.sizeX;
    }
    @Override
    public int getChunkY(int y) {
        return y / Chunk.sizeY;
    }

    @Override
    public Chunk getChunk(int x, int y) {
        if (!chunks.containsKey(Arrays.toString(new int[]{x, y}))){
            serverHandler.writer.println("getChunk_request " + Arrays.toString(new int[]{x, y}));
            chunks.put(Arrays.toString(new int[]{x, y}), new Chunk(x, y, this));
        }

        return chunks.get(Arrays.toString(new int[]{x, y}));
    }

    @Override
    public void dispose() {
        serverHandler.dispose();
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
