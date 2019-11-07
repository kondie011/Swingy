package com.kondiewtc.swingy.model.character;

import com.kondiewtc.swingy.model.artifact.Armor;
import com.kondiewtc.swingy.model.artifact.Weapon;
import com.kondiewtc.swingy.resources.Position;

public class HeroFactory
{
    public static int weaponStrength(String weapon)
    {
        switch (weapon.toLowerCase())
        {
            case "long sword":
                return 80;
            case "short sword":
                return 50;
            case "gun":
                return 100;
            case "knife":
                return 30;
            default:
                return 10;
        }
    }

    public static int armorStrength(String armor)
    {
        switch (armor.toLowerCase())
        {
            case "steel armor":
                return 100;
            case "carbon fibre armor":
                return 90;
            case "wood armor":
                return 50;
            case "leather armor":
                return 70;
            default:
                return 10;
        }
    }

    public static Character createHero(String name, String heroClass, int level, int exp, Weapon weapon, Armor armor, Position position)
    {
        switch (heroClass.toLowerCase())
        {
            case "elf":
                return new Elf(name, heroClass, level, exp, weapon, armor, 10, position);
            case "knight":
                return new Knight(name, heroClass, level, exp, weapon, armor, 10, position);
            case "mage":
                return new Mage(name, heroClass, level, exp, weapon, armor, 10, position);
            case "viking":
                return new Viking(name, heroClass, level, exp, weapon, armor, 10, position);
            default:
                return new Elf(name, heroClass, level, exp, weapon, armor, 10, position);
        }
    }
}
