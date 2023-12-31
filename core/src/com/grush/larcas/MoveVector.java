package com.grush.larcas;

public class MoveVector implements Cloneable{
    float dx;
    float dy;
    float max;
    boolean lockX = true;
    boolean lockY = true;

    public MoveVector(float dx, float dy, float max){
        this.dx = dx;
        this.dy = dy;
        this.max = max;
    }

    public void addX(float x){
        if (lockX){
            if (x > 0) dx = Math.min(max, dx + x);
            else dx = Math.max(-max, dx + x);
        } else {
            dx+= x;
        }

    }

    public void addY(float y){
        if (lockY){
            if (y > 0) dy = Math.min(max, dy + y);
            else dy = Math.max(-max, dy + y);
        } else {
            dy+= y;
        }
    }

    public void transform(float value){
        this.dx = value*this.dx;
        this.dy = value*this.dy;
    }

    public void decelerateAccelerationX(float speed){
        if (Math.abs(dx) > 0.01) this.dx -=(dx > 0 ? 1 : -1)*speed;
        else dx = 0;

    }

    public void decelerateAccelerationY(float speed){
        if (Math.abs(dy) > 0.01) this.dy -=(dy > 0 ? 1 : -1)*speed;
        else dy = 0;
    }

    @Override
    public MoveVector clone() {
        try {
            @SuppressWarnings("unchecked")
            MoveVector cloned = (MoveVector) super.clone();
            cloned.dx = this.dx;
            cloned.dy = this.dy;
            cloned.max = this.max;
            cloned.lockX = this.lockX;
            cloned.lockY = this.lockY;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
