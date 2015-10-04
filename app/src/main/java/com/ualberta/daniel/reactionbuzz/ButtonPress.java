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
