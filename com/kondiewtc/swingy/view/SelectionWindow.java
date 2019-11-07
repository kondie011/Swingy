package com.kondiewtc.swingy.view;

import com.kondiewtc.swingy.model.character.HeroFactory;

import javax.swing.*;
import java.awt.*;

public class SelectionWindow
{
    private int index;
    private JButton selectButton, newButton;

    public SelectionWindow(String[] heroesArr, JFrame frame)
    {
        int len = heroesArr.length;
        index = 0;
        String names[] = new String[len];
        String level[] = new String[len];
        String exp[] = new String[len];
        String weapon[] = new String[len];
        String armor[] = new String[len];
        String heroClass[] = new String[len];
        JList heroNames = new JList(names);
        JPanel buttons = new JPanel();
        selectButton = new JButton("Select");
        newButton = new JButton("New hero");

        for (int c=0; c<len; c++)
        {
            names[c] = heroesArr[c].split(",")[0];
            heroClass[c] = heroesArr[c].split(",")[1];
            level[c] = heroesArr[c].split(",")[2];
            exp[c] = heroesArr[c].split(",")[3];
            weapon[c] = heroesArr[c].split(",")[4];
            armor[c] = heroesArr[c].split(",")[5];
        }

        JLabel nothing = new JLabel("");

        JLabel header = new JLabel("Select a hero");
        header.setBounds(255, 10, 200, 20);
        Font font = header.getFont();
        header.setFont(font.deriveFont(font.getStyle() | Font.BOLD));

        JLabel lblName = new JLabel("Heroes:");
        lblName.setBounds(200, 40, 250, 20);
        heroNames.setVisibleRowCount(5);
        heroNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        heroNames.setBounds(20, 60, 300, 400);

        selectButton.setForeground(new Color(10, 50, 150));
        newButton.setForeground(new Color(10, 50, 150));
        buttons.add(selectButton);
        buttons.add(newButton);

        JLabel lblDetails = new JLabel("Hero details:");
        lblDetails.setBounds(400, 40, 350, 20);
        JTextArea details = new JTextArea();
        details.setEditable(false);
        details.setBounds(350, 60, 400, 400);

        heroNames.addListSelectionListener(e -> {
            index = heroNames.getSelectedIndex();
            details.setText("Name: " + names[index] + "\n" +
                        "Hero class: " + heroClass[index] + "\n" +
                        "Level: " + level[index] + "\n" +
                        "Experience: " + exp[index] + "\n" +
                        "Attack/Weapon: " + weapon[index] + "\n" +
                        "Hit points: " + HeroFactory.weaponStrength(weapon[index]) + "\n" +
                        "Defence/Armor: " + armor[index] + "(" + HeroFactory.armorStrength(armor[index]) + "%)");
        });

        frame.getContentPane().add(BorderLayout.SOUTH, buttons);
        frame.getContentPane().add(heroNames);
        frame.getContentPane().add(details);
        frame.getContentPane().add(lblDetails);
        frame.getContentPane().add(lblName);
        frame.getContentPane().add(header);
        frame.getContentPane().add(nothing);
        frame.setVisible(true);
    }

    public JButton getNewButton() {
        return newButton;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public int getIndex() {
        return index;
    }
}
