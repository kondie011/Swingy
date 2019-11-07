package com.kondiewtc.swingy.view;

import javax.swing.*;
import java.awt.*;

public class CreateHeroWindow {

    private JTextField name;
    private JComboBox heroClass, weapon, armor;
    private JButton saveButton, cancelButton;
    private JLabel header;

    public CreateHeroWindow(JFrame frame)
    {
        JPanel buttons = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        String[] heroClassArr = {"knight", "mage", "viking", "elf"};
        String[] weaponArr = {"long sword", "short sword", "knife", "gun"};
        String[] armorArr = {"steel armor", "carbon fibre armor", "wood armor", "leather armor"};

        JLabel nothing = new JLabel("");

        header = new JLabel("Fill in the form:");
        header.setBounds(255, 30, 200, 14);
        Font font = header.getFont();
        header.setFont(font.deriveFont(font.getStyle() | Font.BOLD));

        name = new JTextField();
        name.setBounds(268, 100, 190, 20);
        name.setColumns(10);
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(145, 100, 100, 14);

        JLabel lblClass = new JLabel("Choose the hero class:");
        lblClass.setBounds(145, 150, 200, 14);
        heroClass = new JComboBox(heroClassArr);
        heroClass.setBounds(345, 150, 116, 14);

        JLabel lblWeapon = new JLabel("Choose a weapon:");
        lblWeapon.setBounds(145, 200, 200, 14);
        weapon = new JComboBox(weaponArr);
        weapon.setBounds(345, 200, 116, 14);

        JLabel lblArmor = new JLabel("Choose your armor:");
        lblArmor.setBounds(145, 250, 200, 14);
        armor = new JComboBox(armorArr);
        armor.setBounds(345, 250, 116, 14);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        saveButton.setForeground(new Color(10, 50, 150));
        cancelButton.setForeground(new Color(10, 50, 150));
        buttons.add(saveButton);
        buttons.add(cancelButton);

        frame.getContentPane().add(name);
        frame.getContentPane().add(lblName);
        frame.getContentPane().add(heroClass);
        frame.getContentPane().add(lblClass);
        frame.getContentPane().add(weapon);
        frame.getContentPane().add(lblWeapon);
        frame.getContentPane().add(armor);
        frame.getContentPane().add(lblArmor);
        frame.getContentPane().add(header);
        frame.getContentPane().add(nothing);
        frame.getContentPane().add(BorderLayout.SOUTH, buttons);
        frame.setVisible(true);
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JComboBox getArmor() {
        return armor;
    }

    public JComboBox getHeroClass() {
        return heroClass;
    }

    public JComboBox getWeapon() {
        return weapon;
    }

    public JTextField getName() {
        return name;
    }

    public JLabel getHeader() {
        return header;
    }
}
