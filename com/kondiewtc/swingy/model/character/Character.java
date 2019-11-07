package com.kondiewtc.swingy.model.character;

import com.kondiewtc.swingy.controller.GamePlayController;
import com.kondiewtc.swingy.model.artifact.Armor;
import com.kondiewtc.swingy.model.artifact.Helm;
import com.kondiewtc.swingy.model.artifact.Weapon;
import com.kondiewtc.swingy.resources.Position;
import com.kondiewtc.swingy.view.GamePlayWindow;

public class Character {

    protected String name;
    protected String heroClass;
    protected int level, exp, hitP, fights, runAways;
    protected Weapon weapon;
    protected Armor armor;
    protected Helm helm;
    protected Position position;

    public Character (String name, String heroClass, int level, int exp, Weapon weapon, Armor armor, int hitP, Position position)
    {
        this.name = name;
        this.heroClass = heroClass;
        this.level = level;
        this.exp = exp;
        this.hitP = hitP;
        this.position = position;
        this.weapon = weapon;
        this.armor = armor;
        this.helm = null;
        this.fights = 0;
        this.runAways = 0;
    }

    public Position getPosition() {
        return position;
    }

    public boolean move(String direction) {

        int size = (level-1)*5+10-(level%2);
        if (position.getX() != 0 && position.getX() != size-1 && position.getY() != 0 && position.getY() != size-1) {
            switch (direction) {
                case "NORTH":
                    position.setY(position.getY() - 1);
                    break;
                case "SOUTH":
                    position.setY(position.getY() + 1);
                    break;
                case "EAST":
                    position.setX(position.getX() - 1);
                    break;
                case "WEST":
                    position.setX(position.getX() + 1);
                    break;
            }
            if (position.getX() == 0 || position.getX() == size-1 || position.getY() == 0 || position.getY() == size-1)
            {
                return true;
            }
        }
        else
        {
            //this means you won the game
            return true;
        }
        //this means you haven't reached the wall
        return false;
    }

    public boolean run()
    {
        int random = (int) (Math.random() * ((2 - 0) + 0));
        if (random == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean fight(Villain villain)
    {
        int sum = villain.level + this.level;
        int random = (int) (Math.random() * sum);

        if (random <= this.level + 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Armor getArmor() {
        return armor;
    }

    public int getExp() {
        return exp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getRunAways() {
        return runAways;
    }

    public int getFights() {
        return fights;
    }

    public void setRunAways(int runAways) {
        this.runAways = runAways;
    }

    public void setFights(int fights) {
        this.fights = fights;
    }
}
