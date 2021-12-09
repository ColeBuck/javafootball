package com.cole.javafootball;

import java.util.ArrayList;
import java.util.HashMap;

enum DepthChartPosition { // note the additional positions here: KR, PR, etc.
    QB, RB, FB, WR, TE, LT, LG, C, RG, RT, DT, LE, RE, MLB, ROLB, LOLB, CB, FS, SS, K, P, LS, KR, PR;
}

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
