package com.cole.javafootball;

import java.util.ArrayList;
import java.util.HashMap;

public class DepthChart extends HashMap<DepthChartPosition, ArrayList<Player>> {

    public DepthChart() {
        for (DepthChartPosition dcp : DepthChartPosition.values()) {
            // initialize default ratings
            this.put(dcp, new ArrayList<Player>());
        }
    }

    public void addPlayer(DepthChartPosition position, Player player) {
        this.get(position).add(player);
    }

}
