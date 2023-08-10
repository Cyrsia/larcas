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
    public float blockSize = 20;
    public final int renderDistance = 50;
    public void update(){
        if (Gdx.input.isKeyPressed(Input.Keys.U)){
            if (this.blockSize <= 32){
                this.blockSize+=0.1f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.J)){
            if (this.blockSize >= 4){
                this.blockSize-=0.1f;
            }
        }

        blockX = (int) ((Gdx.input.getX() + coordinate.x) / blockSize); // Высчитываются координаты мышки по мерке мира игры
        blockY = (int) ((Gdx.graphics.getHeight() - Gdx.input.getY() + coordinate.y) / blockSize);
        blockCoordinate.x = blockX;
        blockCoordinate.y = blockY;

        updateP(Player.PLAYER.coordinate);
    }

    public void updateP(Coordinate<Float> player){
        this.coordinate.x = (player.x+Player.PLAYER.size[0]/2 - 0.5f*Gdx.graphics.getWidth()/blockSize) * blockSize;
        this.coordinate.y = (player.y+Player.PLAYER.size[1]/2 - 0.5f*Gdx.graphics.getHeight()/blockSize) * blockSize;
    }
}
