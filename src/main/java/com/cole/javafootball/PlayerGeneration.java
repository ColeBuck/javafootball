package com.cole.javafootball;

import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.Random;

import com.cole.javafootball.Player.Position;

import java.io.*;

public class PlayerGeneration {
    private static ArrayList<String> firstNames = new ArrayList<String>();
    private static ArrayList<String> lastNames = new ArrayList<String>();

    public static String generateFirstName() {
        Random rand = new Random();
        return firstNames.get(rand.nextInt(firstNames.size()));
    }

    public static String generateLastName() {
        Random rand = new Random();
        return lastNames.get(rand.nextInt(lastNames.size()));
    }

    public static short generateHeight(Position position) {
        Random rand = new Random();
        switch(position) {
            case QB:
                return (short) (rand.nextInt(5) + 73);
            case RB:
                return (short) (rand.nextInt(5) + 69);
            case FB:
                return (short) (rand.nextInt(7) + 70);
            case WR:
                return (short) (rand.nextInt(9) + 68);
            case TE:
                return (short) (rand.nextInt(3) + 75);
            case LT: case LG: case C: case RG: case RT:
                return (short) (rand.nextInt(4) + 75);
            case DT:
                return (short) (rand.nextInt(4) + 74);
            case LE: case RE:
                return (short) (rand.nextInt(4) + 75);
            case LOLB: case MLB: case ROLB:
                return (short) (rand.nextInt(4) + 73);
            case CB: case FS: case SS:
                return (short) (rand.nextInt(5) + 70);
            case K:
                return (short) (rand.nextInt(5) + 69);
            case P:
                return (short) (rand.nextInt(6) + 70);
            case LS:
                return (short) (rand.nextInt(5) + 73);
            default:
                return (short) 74;
        }
    }

    public static short generateWeight(Position position) {
        Random rand = new Random();
        switch(position) {
            case QB:
                return (short) (rand.nextInt(25) + 210);
            case RB:    
                return (short) (rand.nextInt(40) + 190);
            case FB:
                return (short) (rand.nextInt(30) + 225);
            case WR:
                return (short) (rand.nextInt(40) + 185);
            case TE:
                return (short) (rand.nextInt(20) + 240);
            case LT: case LG: case C: case RG: case RT: 
                return (short) (rand.nextInt(40) + 295);
            case DT: 
                return (short) (rand.nextInt(30) + 295);
            case LE: case RE:
                return (short) (rand.nextInt(25) + 265);
            case LOLB: case MLB: case ROLB:
                return (short) (rand.nextInt(25) + 235);
            case CB: case FS: case SS:
                return (short) (rand.nextInt(25) + 185);
            case K: 
                return (short) (rand.nextInt(35) + 175);
            case P:
                return (short) (rand.nextInt(40) + 185);
            case LS:
                return (short) (rand.nextInt(25) + 225);
            default:
                return (short) 200;
        }
    }

    public static void loadNameData() {
        firstNames.clear();
        lastNames.clear();

        // Step 1: load first name data
        try {
            File firstNamesFile = ResourceUtils.getFile("classpath:static/data/FirstNames.txt");
            FileReader fr = new FileReader(firstNamesFile);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            while((line = br.readLine()) != null) {
                firstNames.add(line);  
            }
            
            br.close();

        } catch(Exception e) {
            System.out.println("ERROR: First name data could not be loaded");
        }

        // Step 2: load last name data
        try {
            File lastNamesFile = ResourceUtils.getFile("classpath:static/data/LastNames.txt");
            FileReader fr = new FileReader(lastNamesFile);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            while((line = br.readLine()) != null) {
                lastNames.add(line);    
            }
            
            br.close();

        } catch(Exception e) {
            System.out.println("ERROR: Last name data could not be loaded");
        }
    }
}
