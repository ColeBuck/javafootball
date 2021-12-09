package com.cole.javafootball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.cole.javafootball.Player.Position;

public class RosterFilterSorter {

    public static ArrayList<Player> filterByPosition(ArrayList<Player> roster, Position position) {
        ArrayList<Player> filtered = new ArrayList<Player>();

        for (Player player : roster) {
            if (player.getPosition().equals(position.toString())) {
                filtered.add(player);
            }
        }

        return filtered;
    }

    public static ArrayList<Player> sortByRating(ArrayList<Player> roster, boolean ascending) {
        // make copy of roster
        ArrayList<Player> sorted = new ArrayList<Player>();
        for (Player player : roster) {
            sorted.add(player);
        }

        // sort the copy
        Collections.sort(sorted, new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                if (ascending) {
                    return p1.getRatings().get(Rating.Overall) - p2.getRatings().get(Rating.Overall);
                } else {
                    return p2.getRatings().get(Rating.Overall) - p1.getRatings().get(Rating.Overall);
                }
            }
        });

        return sorted;
    }

    public static ArrayList<Player> sortByLastName(ArrayList<Player> roster, boolean ascending) {
        // make copy of roster
        ArrayList<Player> sorted = new ArrayList<Player>();
        for (Player player : roster) {
            sorted.add(player);
        }

        // sort the copy
        Collections.sort(sorted, new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                if (ascending) {
                    return p1.getLastName().compareTo(p2.getLastName());
                } else {
                    return p2.getLastName().compareTo(p1.getLastName());
                }
            }
        });

        return sorted;
    }
}
