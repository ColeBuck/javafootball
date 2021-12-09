package com.cole.javafootball;

import org.springframework.core.io.ClassPathResource;

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
        switch (position) {
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
        case LT:
        case LG:
        case C:
        case RG:
        case RT:
            return (short) (rand.nextInt(4) + 75);
        case DT:
            return (short) (rand.nextInt(4) + 74);
        case LE:
        case RE:
            return (short) (rand.nextInt(4) + 75);
        case LOLB:
        case MLB:
        case ROLB:
            return (short) (rand.nextInt(4) + 73);
        case CB:
        case FS:
        case SS:
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
        switch (position) {
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
        case LT:
        case LG:
        case C:
        case RG:
        case RT:
            return (short) (rand.nextInt(40) + 295);
        case DT:
            return (short) (rand.nextInt(30) + 295);
        case LE:
        case RE:
            return (short) (rand.nextInt(25) + 265);
        case LOLB:
        case MLB:
        case ROLB:
            return (short) (rand.nextInt(25) + 235);
        case CB:
        case FS:
        case SS:
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

    public static void generateRatings(Player player) {
        Random rand = new Random();

        player.getRatings().setRating(Rating.PassAccuracy, (short) (rand.nextInt(30) + 30));
        player.getRatings().setRating(Rating.PassPower, (short) (rand.nextInt(30) + 30));
        player.getRatings().setRating(Rating.KickAccuracy, (short) (rand.nextInt(30) + 30));
        player.getRatings().setRating(Rating.KickPower, (short) (rand.nextInt(30) + 30));

        switch (player.position) {
        case QB:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(30) + 60));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(20) + 55));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            player.getRatings().setRating(Rating.PassAccuracy, (short) (rand.nextInt(30) + 65));
            player.getRatings().setRating(Rating.PassPower, (short) (rand.nextInt(30) + 65));
            break;
        case RB:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(35) + 65));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(25) + 60));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            break;
        case WR:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(30) + 70));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(25) + 55));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            break;
        case TE:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(25) + 65));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(30) + 60));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            break;
        case LT:
        case LG:
        case C:
        case RG:
        case RT:
        case LS:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(20) + 50));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(35) + 65));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            break;
        case LE:
        case DT:
        case RE:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(20) + 60));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(35) + 65));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            break;
        case LOLB:
        case MLB:
        case ROLB:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(25) + 65));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(25) + 65));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            break;
        case CB:
        case FS:
        case SS:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(25) + 75));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(20) + 60));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            break;
        case K:
        case P:
            player.getRatings().setRating(Rating.Speed, (short) (rand.nextInt(20) + 60));
            player.getRatings().setRating(Rating.Strength, (short) (rand.nextInt(20) + 50));
            player.getRatings().setRating(Rating.Awareness, (short) (rand.nextInt(35) + 55));
            player.getRatings().setRating(Rating.KickAccuracy, (short) (rand.nextInt(35) + 65));
            player.getRatings().setRating(Rating.KickPower, (short) (rand.nextInt(35) + 65));
            break;
        default:
            break;
        }
    }

    public static void loadNameData() {
        firstNames.clear();
        lastNames.clear();

        // Step 1: load first name data
        try {
            ClassPathResource classPathResource = new ClassPathResource("static/data/FirstNames.txt");
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line = null;

            while ((line = br.readLine()) != null) {
                firstNames.add(line);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("ERROR: First name data could not be loaded");
        }

        // Step 2: load last name data
        try {
            ClassPathResource classPathResource = new ClassPathResource("static/data/LastNames.txt");
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line = null;

            while ((line = br.readLine()) != null) {
                lastNames.add(line);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("ERROR: Last name data could not be loaded");
        }
    }
}
