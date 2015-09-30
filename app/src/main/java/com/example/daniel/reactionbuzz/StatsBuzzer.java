package com.example.daniel.reactionbuzz;

import java.util.ArrayList;

/**
 * Created by Daniel on 2015-09-27.
 */
public class StatsBuzzer {

    protected Integer twoplayerbuzzer[] = {0, 0};
    protected Integer threeplayerbuzzer[] = {0, 0, 0};
    protected Integer fourplayerbuzzer[] = {0, 0, 0, 0};

    public void setPlayerCount(int players, int player) {
        if (players == 2) {
            twoplayerbuzzer[player] += 1;
        }

        else if (players == 3) {
            threeplayerbuzzer[player] += 1;
        }

        else if (players == 4) {
            fourplayerbuzzer[player] += 1;
        }
    }

    public Integer[] getTwoplayerbuzzer() {
        return twoplayerbuzzer;
    }

    public Integer[] getThreeplayerbuzzer() {
        return threeplayerbuzzer;
    }

    public Integer[] getFourplayerbuzzer() {
        return fourplayerbuzzer;
    }
}
