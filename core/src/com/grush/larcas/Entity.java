package com.grush.larcas;

import java.util.Map;

abstract class Entity {
    Coordinate<Float> coordinate;
    Map<?, ?> states;

    public Entity(Coordinate<Float> coordinate, Map<?, ?> states){
        this.coordinate = coordinate;
        this.states = states;
    }

}
