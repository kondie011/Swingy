package com.kondiewtc.swingy;

import com.kondiewtc.swingy.controller.GamePlayController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> heroes;
    private static String filename;

    public static void main(String[] args)
    {
        filename = args[0];
        readFile();
        new GamePlayController(heroes);
    }

    public static void readFile()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            heroes = new ArrayList<>();

            while ((line = reader.readLine()) != null)
            {
                heroes.add(line);
            }
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong: " + e.toString());
        }
    }

    public static List<String> getHeroes() {
        return heroes;
    }
}
