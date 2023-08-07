package com.grush.larcas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Camera {
    Coordinate<Float> coordinate = new Coordinate<>(0f, 0f);
    public static Camera INSTANCE = new Camera();
    public Coordinate<Integer> blockCoordinate = new Coordinate<>(0, 0);
    private Camera(){}
    int blockX;
    int blockY;
    public int blockSize = 20;
    public final int renderDistance = 8;
    public void update(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)){
            if (this.blockSize <= 24){
                this.blockSize++;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
            if (this.blockSize >= 16){
                this.blockSize--;
            }
        }

        blockX = (int) ((Gdx.input.getX() + coordinate.x) / blockSize); // Высчитываются координаты мышки по мерке мира игры
        blockY = (int) ((Gdx.graphics.getHeight() - Gdx.input.getY() + coordinate.y) / blockSize);
        blockCoordinate.x = blockX;
        blockCoordinate.y = blockY;

        updateP(Player.PLAYER.coordinate);
    }

    public void updateP(Coordinate<Float> player){
        this.coordinate.x = (player.x - 0.5f*Gdx.graphics.getWidth()/blockSize) * blockSize;
        this.coordinate.y = (player.y - 0.5f*Gdx.graphics.getHeight()/blockSize) * blockSize;
    }
}
