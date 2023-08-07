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
    public int blockSize = 16;
    public void update(){
		blockX = (int) ((Gdx.input.getX() + coordinate.x) / blockSize); // Высчитываются координаты мышки по мерке мира игры
        blockY = (int) ((Gdx.graphics.getHeight() - Gdx.input.getY() + coordinate.y) / blockSize);
        blockCoordinate.x = blockX;
        blockCoordinate.y = blockY;

        updateP(Player.PLAYER.coordinate);

//        if (Gdx.input.isKeyPressed(Input.Keys.W)){
//            this.coordinate.y--;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.S)){
//            this.coordinate.y++;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.D)){
//            this.coordinate.x--;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.A)){
//            this.coordinate.x++;
//        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.U)){
            this.blockSize++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
            this.blockSize--;
        }
    }

    public void updateP(Coordinate<Float> player){
        this.coordinate.x = (player.x - 0.5f*Gdx.graphics.getWidth()/blockSize) * blockSize;
        this.coordinate.y = (player.y - 0.5f*Gdx.graphics.getHeight()/blockSize) * blockSize;
    }
}
