package com.kondiewtc.swingy.resources;

import com.kondiewtc.swingy.model.artifact.Armor;
import com.kondiewtc.swingy.model.artifact.Weapon;
import com.kondiewtc.swingy.model.character.Villain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map
{
    private static int size;
    private List<Villain> villains;
    private String names[] = {"Goblin", "Joker", "the bad guy"};
    private String heroClasses[] = {"Something", "The joker", "human"};
    private String weapon[] = {"knife", "jokes", "gun"};
    private String armor[] = {"skin", "none", "jacket"};
    private int heroLevel;

    public Map(int level)
    {
        heroLevel = level;
        generateSize();
        villains = new ArrayList<>();
        createVillains();
    }

    public void generateSize()
    {
        size = (heroLevel-1)*5+10-(heroLevel%2);
    }

    public static int randomInt(int start, int end)
    {
        if (end - start > 0) {
            return new Random().nextInt(end - start) + start;
        }else{
            return new Random().nextInt(start - end) + start;
        }
    }

    public void createVillains()
    {
        int i = 0, level, exp;

        for (int c=0; c<(size * size) / 2; c++)
        {
            if (i >= names.length)
            {
                i = 0;
            }
            level = randomInt(3, heroLevel * 2);
            exp = level * 1000 + (level - 1) * 2 * 450;
            Position position = new Position(randomInt(1, size - 2), randomInt(1, size - 1));
            Villain villain = new Villain(names[i], heroClasses[i], level, exp, new Weapon(weapon[i], randomInt(0, 100)), new Armor(armor[i], randomInt(0, 100)), randomInt(0, 100), position);
            villains.add(villain);
            i++;
        }
    }

    public static int getSize() {
        return size;
    }

    public List<Villain> getVillains() {
        return villains;
    }
}
