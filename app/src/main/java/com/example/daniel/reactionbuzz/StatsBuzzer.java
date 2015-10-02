package com.example.daniel.reactionbuzz;

import java.util.ArrayList;
import java.util.Arrays;

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

    public int getTwoplayerbuzzer(int index) {
        return twoplayerbuzzer[index];
    }

    public int getThreeplayerbuzzer(int index) {
        return threeplayerbuzzer[index];
    }

    public int getFourplayerbuzzer(int index) {
        return fourplayerbuzzer[index];
    }

    public void clear() {
        Arrays.fill(twoplayerbuzzer, 0);
        Arrays.fill(threeplayerbuzzer, 0);
        Arrays.fill(fourplayerbuzzer, 0);
    }
}
