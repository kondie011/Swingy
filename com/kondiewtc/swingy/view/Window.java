package com.kondiewtc.swingy.view;

import javax.swing.*;
import java.awt.*;

public class Window
{
    public static void main(String args[]){
        JFrame frame = new JFrame("main");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon ii = new ImageIcon("/goinfre/knedzing/Desktop/Java/Swingy/com/kondiewtc/swingy/resources/monster.png");
        JLabel label = new JLabel(ii);
        JScrollPane jsp = new JScrollPane(label);

        frame. setSize(1000, 700);

        frame.getContentPane().add(label);

        frame.setVisible(true);
    }
}
