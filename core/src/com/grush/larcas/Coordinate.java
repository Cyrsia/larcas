package com.grush.larcas;

public class Coordinate<T> implements Cloneable {
    public T x;
    public T y;
    public Coordinate(T x, T y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Coordinate<T> clone() {
        try {
            @SuppressWarnings("unchecked")
            Coordinate<T> cloned = (Coordinate<T>) super.clone();
            cloned.x = this.x;
            cloned.y = this.y;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
