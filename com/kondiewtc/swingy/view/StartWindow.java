package com.kondiewtc.swingy.view;

import javax.swing.*;
import java.awt.*;

public class StartWindow
{
    private JFrame frame;
    private JButton selectHeroButton;

    public StartWindow()
    {
        frame = new JFrame("Swingy");
        selectHeroButton = new JButton("Welcome to Swingy... Press to start the game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,550);
        selectHeroButton.setForeground(new Color(10, 50, 150));

        frame.getContentPane().add(BorderLayout.CENTER, selectHeroButton);
        frame.setVisible(true);
    }

    public JButton getSelectHeroButton() {
        return selectHeroButton;
    }

    public JFrame getFrame() {
        return frame;
    }
}
