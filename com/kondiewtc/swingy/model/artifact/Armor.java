package com.kondiewtc.swingy.model.artifact;

public class Armor
{
    private String name;
    private int strength;

    public Armor(String name, int strength)
    {
        this.name = name;
        this.strength = strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }
}
