package com.grush.larcas;

public class Damage {
    int damage;
    EDamageType[] damageTypes;

    public Damage(int damage, EDamageType[] damageTypes){
        this.damage = damage;
        this.damageTypes = damageTypes;
    }
    public Damage(int damage, EDamageType damageType){
        this.damage = damage;
        this.damageTypes = new EDamageType[]{damageType};
    }
}
