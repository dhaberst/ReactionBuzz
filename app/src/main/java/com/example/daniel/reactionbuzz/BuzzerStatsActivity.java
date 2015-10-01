package com.example.daniel.reactionbuzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BuzzerStatsActivity extends AppCompatActivity {

    private static final String FILENAME = "buzzerStat.sav";

    StatsBuzzer statsbuzzer = new StatsBuzzer();

    TextView playerTwosText;
    TextView playerThreesText;
    TextView playerFoursText;

    int playerone = 0;
    int playertwo = 1;
    int playerthree = 2;
    int playerfour = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buzzerstats);

        loadFromFile();

        playerTwosText = (TextView)findViewById(R.id.twoplayerstats);
        playerThreesText = (TextView)findViewById(R.id.threeplayerstats);
        playerFoursText = (TextView)findViewById(R.id.fourplayerstats);

        playerTwosText.setText("Player 1 buzzes: "+Integer.toString(statsbuzzer.getTwoplayerbuzzer(playerone))
                +"\nPlayer 2 buzzes: "+Integer.toString(statsbuzzer.getTwoplayerbuzzer(playertwo)));

        playerThreesText.setText("Player 1 buzzes: "+Integer.toString(statsbuzzer.getThreeplayerbuzzer(playerone))
                +"\nPlayer 2 buzzes: "+Integer.toString(statsbuzzer.getThreeplayerbuzzer(playertwo))
                +"\nPlayer 3 buzzes: "+Integer.toString(statsbuzzer.getThreeplayerbuzzer(playerthree)));

        playerFoursText.setText("Player 1 buzzes: "+Integer.toString(statsbuzzer.getFourplayerbuzzer(playerone))
                +"\nPlayer 2 buzzes: "+Integer.toString(statsbuzzer.getFourplayerbuzzer(playertwo))
                +"\nPlayer 3 buzzes: "+Integer.toString(statsbuzzer.getFourplayerbuzzer(playerthree))
                +"\nPlayer 4 buzzes: "+Integer.toString(statsbuzzer.getFourplayerbuzzer(playerfour)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buzzer_stats, menu);
        return true;
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
