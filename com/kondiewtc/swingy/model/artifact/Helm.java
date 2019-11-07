package com.kondiewtc.swingy.model.artifact;

public class Helm
{
    private String name;
    private int addedHitP;

    public Helm(String name, int addedHitP)
    {
        this.name = name;
        this.addedHitP = addedHitP;
    }

    public String getName() {
        return name;
    }

    public int getAddedHitP() {
        return addedHitP;
    }

    public void setAddedHitP(int addedHitP) {
        this.addedHitP = addedHitP;
    }
}
