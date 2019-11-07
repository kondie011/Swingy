package com.kondiewtc.swingy.model.character;

import com.kondiewtc.swingy.model.artifact.Armor;
import com.kondiewtc.swingy.model.artifact.Weapon;
import com.kondiewtc.swingy.resources.Position;

public class Villain extends Character {

    public Villain(String name, String heroClass, int level, int exp, Weapon weapon, Armor armor, int hitP, Position position) {
        super(name, heroClass, level, exp, weapon, armor, hitP, position);
    }
}
