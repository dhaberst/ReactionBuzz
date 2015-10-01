package com.example.daniel.reactionbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TwoPlayerGame extends AppCompatActivity {

    private static final String FILENAME = "buzzerStat.sav";

    StatsBuzzer statsbuzzer = new StatsBuzzer();

    int players = 2;

    int playerone = 0;
    int playertwo = 1;

    Button oneplayerbutton;
    Button twoplayerbutton;

    public ButtonPress pressed = new ButtonPress();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoplayers);

        loadFromFile();

        oneplayerbutton = (Button)findViewById(R.id.oneplayerbutton);
        twoplayerbutton = (Button)findViewById(R.id.twoplayerbutton);

        // Player one's button
        oneplayerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed.onPress(oneplayerbutton);
                startActivity(new Intent(TwoPlayerGame.this, PlayOnePop.class));
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
                startActivity(new Intent(TwoPlayerGame.this, PlayTwoPop.class));
                pressed.onReset(twoplayerbutton);

                statsbuzzer.setPlayerCount(players, playertwo);
                saveInFile();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_player_game, menu);
        return true;
    }

    public void buzzerstats(MenuItem menu) {
        Intent intent = new Intent(TwoPlayerGame.this, BuzzerStatsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
