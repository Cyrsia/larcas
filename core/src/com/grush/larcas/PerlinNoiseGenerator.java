package com.grush.larcas;

import java.util.Random;

public class PerlinNoiseGenerator {
    private final int width;
    private final int height;
    private final float[][] noiseMap;
    long seed;

    public PerlinNoiseGenerator(int width, int height, long seed) {
        this.width = width;
        this.height = height;
        this.noiseMap = new float[width][height];
        this.seed = seed;
    }

    public float[][] generateNoiseMap(int xx, int yy){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                noiseMap[y][x] = OpenSimplex2S.noise2(this.seed, (double) (xx * Chunk.sizeX + x) /24, (double) (yy * Chunk.sizeY + y) /24);
            }
        }
        return noiseMap;
    }
}
