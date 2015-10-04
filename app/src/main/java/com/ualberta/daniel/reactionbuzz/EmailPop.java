package com.ualberta.daniel.reactionbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

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

/**
 * Created by Daniel on 2015-09-26.
 */
public class EmailPop extends Activity {

    Button send;
    EditText email;

    int playerone = 0;
    int playertwo = 1;
    int playerthree = 2;
    int playerfour = 3;

    private static final String FILENAME1 = "buzzerStat.sav";
    private static final String FILENAME2 = "reactStat.sav";

    StatsBuzzer statsbuzzer = new StatsBuzzer();
    StatsReaction statistics = new StatsReaction();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.emailstats);

        loadFromFileReaction();
        loadFromFileBuzzer();

        final String emailstats = "REACTION TIME STATISTICS:\n\n"
                +statistics.returnArrayList()+"\n"
                +statistics.getTextView()+"\n\n"
                +"BUZZER COUNTS:\n\n"
                +"2 Players\n\n"
                +"Player 1 buzzes: "+Integer.toString(statsbuzzer.getTwoplayerbuzzer(playerone))
                +"\nPlayer 2 buzzes: "+Integer.toString(statsbuzzer.getTwoplayerbuzzer(playertwo))
                +"\n\n3 Players\n\n"
                +"Player 1 buzzes: "+Integer.toString(statsbuzzer.getThreeplayerbuzzer(playerone))
                +"\nPlayer 2 buzzes: "+Integer.toString(statsbuzzer.getThreeplayerbuzzer(playertwo))
                +"\nPlayer 3 buzzes: "+Integer.toString(statsbuzzer.getThreeplayerbuzzer(playerthree))
                +"\n\n4 Players\n\n"
                +"Player 1 buzzes: "+Integer.toString(statsbuzzer.getFourplayerbuzzer(playerone))
                +"\nPlayer 2 buzzes: "+Integer.toString(statsbuzzer.getFourplayerbuzzer(playertwo))
                +"\nPlayer 3 buzzes: "+Integer.toString(statsbuzzer.getFourplayerbuzzer(playerthree))
                +"\nPlayer 4 buzzes: "+Integer.toString(statsbuzzer.getFourplayerbuzzer(playerfour));

        send = (Button) findViewById(R.id.send);
        email = (EditText) findViewById(R.id.enteremail);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .69), (int) (height * .36));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = email.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, "ReactionBuzz Statistics");
                email.putExtra(Intent.EXTRA_TEXT,emailstats);
                email.setType("meassge/rfc822");
                startActivity(Intent.createChooser(email, "choose:"));

                finish();
            }
        });
    }

    private void loadFromFileBuzzer() {
        try {
            FileInputStream fis = openFileInput(FILENAME1);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015/09/23
            statsbuzzer = gson.fromJson(in, StatsBuzzer.class);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            statsbuzzer = new StatsBuzzer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private void loadFromFileReaction() {
        try {
            FileInputStream fis = openFileInput(FILENAME2);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015/09/23
            statistics = gson.fromJson(in, StatsReaction.class);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            statistics = new StatsReaction();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }
}
