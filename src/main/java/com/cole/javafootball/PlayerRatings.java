package com.cole.javafootball;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import com.cole.javafootball.Player.Position;

enum Rating {
    Overall, Speed, Strength, Awareness, PassPower, PassAccuracy, KickPower, KickAccuracy
}

public class PlayerRatings extends HashMap<Rating, Short> {

    Logger logger = LoggerFactory.getLogger(PlayerRatings.class);

    Position position; // position of the player that these stats are associated with

    public PlayerRatings(Position position) {
        this.position = position;

        for (Rating rating : Rating.values()) {
            // initialize default ratings
            this.put(rating, (short) 50);
        }
    }

    public void setRating(Rating rating, Short value) {
        if (rating == Rating.Overall) {
            logger.warn("Cannot directly set overall rating");
        } else {
            if (value > 99) {
                value = 99; // max value
            }
            if (value < 0) {
                value = 0; // min value
            }
            this.put(rating, value);
            // calculate new overall rating
            calculateOverall();
        }
    }

    public void calculateOverall() {
        short sumRatings = 0;
        short numRatings = 0;
        ArrayList<Rating> positionRatings;

        switch (position) {
        case QB:
            positionRatings = new ArrayList<Rating>() {
                {
                    add(Rating.Speed);
                    add(Rating.Awareness);
                    add(Rating.PassAccuracy);
                    add(Rating.PassPower);
                }
            };
            break;
        case LT:
        case LG:
        case C:
        case RG:
        case RT:
        case LS:
            positionRatings = new ArrayList<Rating>() {
                {
                    add(Rating.Awareness);
                    add(Rating.Strength);
                }
            };
            break;
        case K:
        case P:
            positionRatings = new ArrayList<Rating>() {
                {
                    add(Rating.Awareness);
                    add(Rating.KickAccuracy);
                    add(Rating.KickPower);
                }
            };
            break;
        default:
            positionRatings = new ArrayList<Rating>() {
                {
                    add(Rating.Speed);
                    add(Rating.Awareness);
                    add(Rating.Strength);
                }
            };
            break;
        }

        for (Rating rating : positionRatings) {
            sumRatings = (short) (sumRatings + this.get(rating));
            numRatings++;
        }
        this.put(Rating.Overall, (short) (sumRatings / numRatings));
    }

    // get rating by its String name (mainly for use by Thymeleaf)
    public Short get(String rating) {
        return this.get(Rating.valueOf(rating));
    }
}
