package com.example.daniel.reactionbuzz;

import java.util.ArrayList;

/**
 * Created by Daniel on 2015-09-17.
 */
public class StatsReaction {

    protected ArrayList<Float> reactionList;

    public void addTime(Float time) {
        reactionList.add(time);
    }

    public ArrayList returnList() {
        return reactionList;
    }

    public void clear() {

    }

    public void getMax() {

    }

    public void getMin() {

    }

    public void getMedian() {

    }

}
