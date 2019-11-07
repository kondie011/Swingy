package com.kondiewtc.swingy.view;

import com.kondiewtc.swingy.model.artifact.Armor;
import com.kondiewtc.swingy.model.artifact.Weapon;
import com.kondiewtc.swingy.resources.Position;

import javax.swing.*;
import java.awt.*;

public class GamePlayWindow
{
    private JTextArea heroDetails;
    private static JTextArea logs;
    private JButton fightButton, runButton, restartButton, takeWeaponButton;
    JComboBox directionCombo;
    ImageIcon ii;
    JLabel image;

    public GamePlayWindow(JFrame frame)
    {
        String directions[] = {"NORTH", "SOUTH", "EAST", "WEST"};
        JPanel buttons = new JPanel();
        directionCombo = new JComboBox(directions);
        heroDetails = new JTextArea();
        logs = new JTextArea();

        JLabel nothing = new JLabel("");

        fightButton = new JButton("Fight");
        runButton = new JButton("Run");
        restartButton = new JButton("restart");
        takeWeaponButton = new JButton("Take weapon");
        fightButton.setForeground(new Color(10, 50, 150));
        runButton.setForeground(new Color(10, 50, 150));
        restartButton.setForeground(new Color(10, 50, 150));
        takeWeaponButton.setForeground(new Color(10, 50, 150));
        buttons.add(directionCombo);
        buttons.add(fightButton);
        buttons.add(runButton);
        buttons.add(restartButton);
        buttons.add(takeWeaponButton);
        restartButton.setVisible(false);
        takeWeaponButton.setVisible(false);
        logs.setEditable(false);
        heroDetails.setEditable(false);
        logs.setBounds(350, 40, 430, 220);
        heroDetails.setBounds(20, 40, 300, 450);

        JLabel lblLogs = new JLabel("Logs:");
        lblLogs.setBounds(450, 20, 330, 20);

        JLabel lblDetails = new JLabel("Your details:");
        lblDetails.setBounds(50, 20, 330, 20);

        image = new JLabel();
        image.setBounds(350, 240, 430, 250);

        frame.getContentPane().add(BorderLayout.SOUTH, buttons);
        frame.getContentPane().add(logs);
        frame.getContentPane().add(heroDetails);
        frame.getContentPane().add(lblDetails);
        frame.getContentPane().add(lblLogs);
        frame.getContentPane().add(image);
        frame.getContentPane().add(nothing);
        frame.setVisible(true);
        fightButton.setVisible(false);
        runButton.setVisible(false);
    }

    public ImageIcon getIi() {
        return ii;
    }

    public void setImage(String path) {
        ii = new ImageIcon(path);
        image.setIcon(ii);
    }

    public JComboBox getDirectionCombo() {
        return directionCombo;
    }

    public JButton getFightButton() {
        return fightButton;
    }

    public JButton getRunButton() {
        return runButton;
    }

    public static void displayMessage(String str)
    {
        logs.setText(str);
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton getTakeWeaponButton() {
        return takeWeaponButton;
    }

    public void setValues(String name, String heroClass, int level, int exp, Weapon weapon, Armor armor, Position position, int mapSize, int fights, int runAways)
    {
        heroDetails.setText("Map size: " + (mapSize - 1) + "x" + (mapSize - 1) + "\n" +
                "Name: " + name + "\n" +
                "Hero class: " + heroClass + "\n" +
                "Life: " + "100" + "\n" +
                "Level: " + level + "\n" +
                "Experience: " + exp + " XP\n" +
                "Position: " + position.getX() + " : " + position.getY() + "\n" +
                "Fights won: " + fights + "\n" +
                "Run aways: " + runAways + "\n" +
                "Attack/Weapon: " + weapon.getName() + "\n" +
                "Hit points: " + weapon.getDamage() + "\n" +
                "Defence/Armor: " + armor.getName() + "(" + armor.getStrength() + "%)\n");
    }
}
