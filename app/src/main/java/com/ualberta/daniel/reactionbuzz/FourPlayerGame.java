package com.ualberta.daniel.reactionbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FourPlayerGame extends AppCompatActivity {

    private static final String FILENAME = "buzzerStat.sav";

    StatsBuzzer statsbuzzer = new StatsBuzzer();

    int players = 4;

    int playerone = 0;
    int playertwo = 1;
    int playerthree = 2;
    int playerfour = 3;

    Button oneplayerbutton;
    Button twoplayerbutton;
    Button threeplayerbutton;
    Button fourplayerbutton;

    public ButtonPress pressed = new ButtonPress();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourplayers);

        loadFromFile();

        oneplayerbutton = (Button)findViewById(R.id.oneplayerbutton);
        twoplayerbutton = (Button)findViewById(R.id.twoplayerbutton);
        threeplayerbutton = (Button)findViewById(R.id.threeplayerbutton);
        fourplayerbutton = (Button)findViewById(R.id.fourplayerbutton);


        // Player one's button
        oneplayerbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pressed.onPress(oneplayerbutton);
            startActivity(new Intent(FourPlayerGame.this, PlayOnePop.class));
            pressed.onReset(oneplayerbutton);

            statsbuzzer.setPlayerCount(players, playerone);
            saveInFile();
        }
    });

    // Player two's button
    twoplayerbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pressed.onPress(twoplayerbutton);
            startActivity(new Intent(FourPlayerGame.this,PlayTwoPop.class));
            pressed.onReset(twoplayerbutton);

            statsbuzzer.setPlayerCount(players, playertwo);
            saveInFile();
        }
    });

    // Player three's button
    threeplayerbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pressed.onPress(threeplayerbutton);
            startActivity(new Intent(FourPlayerGame.this,PlayThreePop.class));
            pressed.onReset(threeplayerbutton);

            statsbuzzer.setPlayerCount(players, playerthree);
            saveInFile();
        }
    });

    // Player four's button
    fourplayerbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pressed.onPress(fourplayerbutton);
            startActivity(new Intent(FourPlayerGame.this,PlayFourPop.class));
            pressed.onReset(fourplayerbutton);

            statsbuzzer.setPlayerCount(players, playerfour);
            saveInFile();
        }
    });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_four_player_game, menu);
        return true;
    }

    public void buzzerstats(MenuItem menu) {
        Intent intent = new Intent(FourPlayerGame.this, BuzzerStatsActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    //Derived from from 301 lab
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
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

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(statsbuzzer, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
