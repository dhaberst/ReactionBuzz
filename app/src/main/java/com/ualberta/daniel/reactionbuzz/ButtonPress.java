package com.ualberta.daniel.reactionbuzz;

import android.graphics.drawable.TransitionDrawable;
import android.widget.Button;

/**
 * Created by Daniel on 2015-09-14.
 */

public class ButtonPress {

    public void onPress(Button buttonpress) {

        //changing button to a different color
        TransitionDrawable transition = (TransitionDrawable) buttonpress.getBackground();
        transition.startTransition(0);

        return;
    }

    public void onReset(Button buttonreset) {

        TransitionDrawable transition = (TransitionDrawable) buttonreset.getBackground();
        transition.reverseTransition(0);

    }


}
