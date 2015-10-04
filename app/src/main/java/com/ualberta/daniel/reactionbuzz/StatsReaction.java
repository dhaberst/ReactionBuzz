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

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Daniel on 2015-09-17.
 */
public class StatsReaction {

    protected ArrayList<Long> reactionList = new ArrayList<>();

    public void addTime(Long time) {
        reactionList.add(time);
    }

    public ArrayList<Long> returnList() {
        return reactionList;
    }

    public void clear() {
        reactionList.clear();
    }

    public Long getMax(int number) {
        if (reactionList.size() >= number) {
            ArrayList<Long> lastNumber = getLastNumberOf(number);

            return Collections.max(lastNumber);
        }

        return 0L;
    }

    public Long getMin(int number) {
        if (reactionList.size() >= number) {
            ArrayList<Long> lastNumber = getLastNumberOf(number);

            return Collections.min(lastNumber);
        }

        return 0L;

    }

    public Long getMedian(int number) {
        if (reactionList.size() >= number) {
            ArrayList<Long> lastNumber = getLastNumberOf(number);

            Collections.sort(lastNumber);

            return lastNumber.get(number / 2);
        }
        return 0L;
    }

    public Long getAverage(int number) {
        if (reactionList.size() >= number) {

            Long avg = 0L;

            ArrayList<Long> lastNumber = getLastNumberOf(number);

            for (int i = 0; i < number; i++) {
                avg += lastNumber.get(i);
            }

            return avg/number;
        }
        return 0L;
    }

    public String returnArrayList() {
        String list = "";

        int len = reactionList.size();

        for (int i = 0; i < len; i++) {
            list = list+reactionList.get(i).toString()+"ms\n";
        }
        return list;
    }

    public String getTextView() {

        return ("Minimum Time\n" +
                "Last 10: "+ getMin(10) + "ms" + "\t\tLast 100: "+ getMin(100) + " ms"
                + "\nMaximum Time\n" +
                "Last 10: "+ getMax(10) + "ms" + "\t\tLast 100: "+ getMax(100) + " ms"
                + "\nAverage Time\n" +
                "Last 10: "+ getAverage(10) + "ms" + "\t\tLast 100: "+ getAverage(100) + " ms"
                + "\nMedian Time\n" +
                "Last 10: "+ getMedian(10) + "ms" + "\t\tLast 100: "+ getMedian(100) + " ms");
    }

    private ArrayList<Long> getLastNumberOf(int number) {

        ArrayList<Long> lastNumber = new ArrayList<>();

        for (int i = number; i > 0; i--) {
            lastNumber.add(reactionList.get(reactionList.size()-i));
        }

        return lastNumber;
    }
}
