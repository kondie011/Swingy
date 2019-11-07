package com.kondiewtc.swingy.controller;

import com.kondiewtc.swingy.Main;
import com.kondiewtc.swingy.model.artifact.Armor;
import com.kondiewtc.swingy.model.artifact.Weapon;
import com.kondiewtc.swingy.model.character.Character;
import com.kondiewtc.swingy.model.character.HeroFactory;
import com.kondiewtc.swingy.model.character.Villain;
import com.kondiewtc.swingy.resources.Position;
import com.kondiewtc.swingy.view.CreateHeroWindow;
import com.kondiewtc.swingy.view.GamePlayWindow;
import com.kondiewtc.swingy.view.SelectionWindow;
import com.kondiewtc.swingy.view.StartWindow;
import com.kondiewtc.swingy.resources.Map;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class GamePlayController {

    private static List<String> heroes;
    private SelectionWindow selectionWindow;
    private StartWindow startWindow;
    private GamePlayWindow gamePlayWindow;
    private CreateHeroWindow createHeroWindow;
    private static String[] heroesArr;
    private JFrame frame;
    private int index = 0;
    private Character hero;
    private String name;
    private String heroClass;
    private int level;
    private int exp;
    private Weapon weapon;
    private Armor armor;
    private Map map;
    private Position position;
    private Position previousPosition;
    private Villain villain;
    private JComboBox dropDown;
    private JButton runButton, fightButton, takeWeaponButton;
    private ImageIcon image;

    public GamePlayController(List<String> heroes)
    {
        this.heroes = heroes;
        convertToArr();
        startWindow = new StartWindow();
        startWindow.getSelectHeroButton().addActionListener(e -> goToSelectionPage());
        frame = startWindow.getFrame();
    }

    private void convertToArr()
    {
        heroesArr = new String[heroes.size()];
        for (int c=0; c<heroes.size(); c++)
        {
            heroesArr[c] = heroes.get(c);
        }
    }

    private void goToSelectionPage()
    {
        frame.getContentPane().removeAll();
        frame.repaint();
        selectionWindow = new SelectionWindow(heroesArr, frame);
        selectionWindow.getSelectButton().addActionListener(e -> {
            index = selectionWindow.getIndex();
            name = heroesArr[index].split(",")[0];
            heroClass = heroesArr[index].split(",")[1];
            level = Integer.parseInt(heroesArr[index].split(",")[2]);
            exp = Integer.parseInt(heroesArr[index].split(",")[3]);
            weapon = new Weapon(heroesArr[index].split(",")[4], HeroFactory.weaponStrength(heroesArr[index].split(",")[4]));
            armor = new Armor(heroesArr[index].split(",")[5], HeroFactory.armorStrength(heroesArr[index].split(",")[5]));

            map = new Map(level);
            position = new Position(map.getSize()/2, map.getSize()/2);
            hero = HeroFactory.createHero(name, heroClass, level, exp, weapon, armor, position);
            goToGamePlay();
        });
        selectionWindow.getNewButton().addActionListener(e -> goToCreationPage());
    }

    private void goToCreationPage()
    {
        frame.getContentPane().removeAll();
        frame.repaint();
        createHeroWindow = new CreateHeroWindow(frame);
        createHeroWindow.getCancelButton().addActionListener(e -> goToSelectionPage());
        createHeroWindow.getSaveButton().addActionListener(e -> {

            if (!createHeroWindow.getName().getText().equals(""))
            {
                writeIntoFile(createHeroWindow.getName().getText() + "," + createHeroWindow.getHeroClass().getSelectedItem() + ",0,0," + createHeroWindow.getWeapon().getSelectedItem() + "," + createHeroWindow.getArmor().getSelectedItem());
                Main.readFile();
                this.heroes = Main.getHeroes();
                convertToArr();
                goToSelectionPage();
            }
            else
            {
                createHeroWindow.getHeader().setText("Please fill in the Name:");
            }
        });
    }

    public static void saveHeroChanges()
    {
        try {
            PrintWriter writer = new PrintWriter("heroes.txt", "UTF-8");

            for (int c=0; c<heroesArr.length; c++)
            {
                writer.println(heroesArr[c]);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void writeIntoFile(String str)
    {
        try
        {
            Main.readFile();
            List<String> lines = Main.getHeroes();
            int count = lines.size();

            PrintWriter writer = new PrintWriter("heroes.txt", "UTF-8");

            for (int c=0; c<count; c++)
            {
                writer.println(lines.get(c));
            }
            if (!str.equals("")) {
                writer.println(str);
            }
            writer.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Couldn't find the file.");
        }
        catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    private void goToGamePlay()
    {
        frame.getContentPane().removeAll();
        frame.repaint();
        gamePlayWindow = new GamePlayWindow(frame);
        fightButton = gamePlayWindow.getFightButton();
        runButton = gamePlayWindow.getRunButton();
        takeWeaponButton = gamePlayWindow.getTakeWeaponButton();
        image = gamePlayWindow.getIi();

        fightButton.addActionListener(e1 -> startFight());
        runButton.addActionListener(e12 -> runAway());
        gamePlayWindow.setValues(name, heroClass, level, exp, weapon, armor, position, map.getSize(), hero.getFights(), hero.getRunAways());
        gamePlayWindow.displayMessage("Move to the wall to win the games.\nIt ain't as easy as you think");
        dropDown = gamePlayWindow.getDirectionCombo();
        takeWeaponButton.addActionListener(e -> {
            hero.getWeapon().setName(villain.getWeapon().getName());
            hero.getWeapon().setDamage(villain.getWeapon().getDamage());
            weapon = new Weapon(hero.getWeapon().getName(), hero.getWeapon().getDamage());
            heroesArr[index] = name + "," + heroClass + "," + level + "," + exp + "," + weapon.getName() + "," + armor.getName();
            saveHeroChanges();
            gamePlayWindow.setValues(name, heroClass, level, exp, weapon, armor, hero.getPosition(), map.getSize(), hero.getFights(), hero.getRunAways());
            takeWeaponButton.setVisible(false);
        });
        gamePlayWindow.getRestartButton().addActionListener(e -> {
            hero.setPosition(new Position(Map.getSize()/2, Map.getSize()/2));
            hero.setRunAways(0);
            hero.setFights(0);
            map.generateSize();
            gamePlayWindow.displayMessage("You know what to do");
            gamePlayWindow.setImage("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/directions.gif");
            gamePlayWindow.getRestartButton().setVisible(false);
            dropDown.setVisible(true);
        });
        dropDown.addActionListener(e -> {
            boolean won;

            takeWeaponButton.setVisible(false);
            previousPosition = new Position(hero.getPosition().getX(), hero.getPosition().getY());
            if (won = hero.move((String) dropDown.getSelectedItem()))
            {
                exp += (500 + (hero.getFights() * 50) - (hero.getRunAways() * 50));
                level = levelFromExp();
                heroesArr[index] = name + "," + heroClass + "," + level + "," + exp + "," + weapon.getName() + "," + armor.getName();
                saveHeroChanges();
                gamePlayWindow.setValues(name, heroClass, level, exp, weapon, armor, hero.getPosition(), map.getSize(), hero.getFights(), hero.getRunAways());
                gamePlayWindow.displayMessage("You WON the game");
                gamePlayWindow.setImage("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/winner.gif");
                dropDown.setVisible(false);
                runButton.setVisible(false);
                fightButton.setVisible(false);
                gamePlayWindow.getRestartButton().setVisible(true);
            }
            gamePlayWindow.setValues(name, heroClass, level, exp, weapon, armor, hero.getPosition(), map.getSize(), hero.getFights(), hero.getRunAways());
            if ((villain = checkForVillain()) != null)
            {
                dropDown.setVisible(false);
                runButton.setVisible(true);
                fightButton.setVisible(true);
                gamePlayWindow.displayMessage("You moved " + dropDown.getSelectedItem() + " and you ran into a monster.\n" + "Name: " + villain.getName() + "\n" +
                                                "Life: " + "100" + "\n" +
                                                "Level: " + villain.getLevel() + "\n" +
                                                "Experience: " + villain.getExp() + " XP\n" +
                                                "Position: " + villain.getPosition().getX() + " : " + position.getY() + "\n" +
                                                "Attack/Weapon: " + villain.getWeapon().getName() + "(" + villain.getWeapon().getDamage() + " hit points)" + "\n" +
                                                "Defence/Armor: " + villain.getArmor().getName() + "(" + villain.getArmor().getStrength() + "% strength)" + "\n");
                gamePlayWindow.setImage("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/monster.gif");
            }
            else if (!won){
                runButton.setVisible(false);
                fightButton.setVisible(false);
                dropDown.setVisible(true);
                gamePlayWindow.displayMessage("You moved " + dropDown.getSelectedItem());
                gamePlayWindow.setImage("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/walking.gif");
            }
        });
    }

    private void runAway()
    {
        if (hero.run())
        {
            runButton.setVisible(true);
            dropDown.setVisible(true);
            hero.setPosition(previousPosition);
            hero.setRunAways(hero.getRunAways() + 1);
            gamePlayWindow.setValues(name, heroClass, level, exp, weapon, armor, hero.getPosition(), map.getSize(), hero.getFights(), hero.getRunAways());
            gamePlayWindow.displayMessage("Lol, you ran from a fight. you lil b...");
            gamePlayWindow.setImage("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/running.gif");
            runButton.setVisible(false);
            fightButton.setVisible(false);
        }
        else
        {
            fightButton.setVisible(true);
            runButton.setVisible(false);
            dropDown.setVisible(false);
            gamePlayWindow.displayMessage("Lol, sorry buddy. you can't run from this one.\nIt's time to FIGHT!!!");
            gamePlayWindow.setImage("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/failed.gif");
        }
    }

    private int levelFromExp()
    {
        int inc = 1450;
        int experience = 1000;
        int c;

        for (c=0; experience < exp; c++)
        {
            experience += inc;
            inc += 900;
        }
        return c;
    }

    private void startFight()
    {
        if (hero.fight(villain))
        {
            exp = (exp + villain.getLevel() * 10);
            level = levelFromExp();
            heroesArr[index] = name + "," + heroClass + "," + level + "," + exp + "," + weapon.getName() + "," + armor.getName();
            saveHeroChanges();
            hero.setFights(hero.getFights() + 1);
            gamePlayWindow.setValues(name, heroClass, level, exp, weapon, armor, hero.getPosition(), map.getSize(), hero.getFights(), hero.getRunAways());
            if (Map.randomInt(0, 2) == 1) {
                gamePlayWindow.displayMessage("Damn, he dead.\nYou won the fight and the villain dropped\na weapon:\n" + villain.getWeapon().getName() + "(" + villain.getWeapon().getDamage() + " hit points)");
                takeWeaponButton.setVisible(true);
            }else{
                gamePlayWindow.displayMessage("Damn, he dead.\nYou won the fight,\nbut the villain didn't drop anything");
            }
            gamePlayWindow.setImage("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/won.gif");
            map.getVillains().remove(villain);
            dropDown.setVisible(true);
            runButton.setVisible(false);
            fightButton.setVisible(false);
            gamePlayWindow.getRestartButton().setVisible(false);
        }
        else
        {
            gamePlayWindow.displayMessage("Yhoo, you got your ass whooped... GAME OVER :(");
            dropDown.setVisible(false);
            runButton.setVisible(false);
            fightButton.setVisible(false);
            gamePlayWindow.getRestartButton().setVisible(true);
            gamePlayWindow.setImage("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/lost.gif");
        }
    }

    private Villain checkForVillain()
    {
        List<Villain> villains = map.getVillains();
        int len = villains.size();

        for (int c=0; c<len; c++)
        {
            if (villains.get(c).getPosition().getX() == hero.getPosition().getX() && villains.get(c).getPosition().getY() == hero.getPosition().getY())
            {
                return villains.get(c);
            }
        }
        return null;
    }
}
