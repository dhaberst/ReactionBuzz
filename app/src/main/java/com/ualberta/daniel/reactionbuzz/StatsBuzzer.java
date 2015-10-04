/* The MIT License (MIT)

        Copyright (c) 2015 dhaberst

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.
*/

package com.ualberta.daniel.reactionbuzz;

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
        // http://stackoverflow.com/questions/9128737/fastest-way-to-set-all-values-of-an-array
        // User: Kurt Du Bois | Accessed: 10/1/2015
        Arrays.fill(twoplayerbuzzer, 0);
        Arrays.fill(threeplayerbuzzer, 0);
        Arrays.fill(fourplayerbuzzer, 0);
    }
}
