package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {
    default void draw(SpriteBatch batch, float x, float y, float width, float height) { //не перезаписывай этот метод пожалуйста
        this.render(batch, x, y, width, height);
    }

    default void render(SpriteBatch batch, float x, float y, float width, float height){
        batch.draw(this.getTexture(), x, y, width, height);
    }

    default Texture getTexture(){
        return null;
    }
}
