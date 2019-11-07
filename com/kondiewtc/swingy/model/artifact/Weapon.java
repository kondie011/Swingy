package com.kondiewtc.swingy.model.artifact;

public class Weapon
{
    private int damage;
    private String name;

    public Weapon(String name, int damage)
    {
        this.name = name;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setName(String name) {
        this.name = name;
    }
}
