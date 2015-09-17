package com.example.daniel.reactionbuzz;

import android.graphics.drawable.TransitionDrawable;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Daniel on 2015-09-14.
 */

public class reactionTime {

    protected ArrayList<Float> reactionList;

    public void addTime(Float time) {
        reactionList.add(time);
    }

    public void onPress(Button buttonpress) {
        // waiting 10 ms
        try {
            Thread.sleep(10);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //changing button to a different color
        TransitionDrawable transition = (TransitionDrawable) buttonpress.getBackground();
        transition.startTransition(0);

        //Start timer

        return;
    }

    public void onReset(Button buttonreset) {

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
