package com.grush.larcas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Camera {
    Coordinate<Integer> coordinate = new Coordinate<Integer>(0,0);
    public static Camera INSTANCE = new Camera();
    private Camera(){}

    public int blockSize = 16;
    public void update(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            this.coordinate.y--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            this.coordinate.y++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            this.coordinate.x--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            this.coordinate.x++;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.U)){
            this.blockSize++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
            this.blockSize--;
        }
    }
}
