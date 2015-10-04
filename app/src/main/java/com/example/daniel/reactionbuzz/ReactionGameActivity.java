package com.example.daniel.reactionbuzz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
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
import java.util.Random;

public class ReactionGameActivity extends AppCompatActivity {

    private static final String FILENAME = "reactStat.sav";

    private StatsReaction statistics;

    Button reactionbutton;
    public ButtonPress reactiontime = new ButtonPress();

    final int minTime = 10;
    final int maxTime = 2000;

    final Handler handler = new Handler();

    Runnable reactionTimer = new Runnable() {
        @Override
        public void run() {
            //Doing something after 10ms-2000ms
            reactiontime.onReset(reactionbutton);
            final long startTime = System.nanoTime();
            reactionbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long endTime = (System.nanoTime() - startTime)/1000000;
                    statistics.addTime(endTime);
                    saveInFile();
                    reactionReturnDialog("Your time was "+ String.valueOf(endTime)+"ms!");
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reactiongame);

        loadFromFile();

        startActivity(new Intent(ReactionGameActivity.this, InfoPop.class));

        preOnClickTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_game, menu);
        return true;
    }

    public void reactionstats(MenuItem menu) {
        Intent intent = new Intent(ReactionGameActivity.this, ReactionStatsActivity.class);
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

    //stackoverflow: http://stackoverflow.com/questions/6029495/how-can-i-generate-random-number-in-specific-range-in-android
    public long randomTime(){
        Random random = new Random();
        return random.nextInt(maxTime - minTime) + minTime;
    }

    public void reactionReturnDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_Dialog));
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                preOnClickTimer();
            }
        });
        builder.show();
    }

    public void preOnClickTimer() {
        reactionbutton = (Button)findViewById(R.id.reactionbutton);
        reactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reactiontime.onPress(reactionbutton);
                // Runnable
                handler.postDelayed(reactionTimer, (int) randomTime());

                // Incorrect Press Handled Here
                reactionbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //handler.removeCallbacks(reactionTimer);
                        reactionReturnDialog("Incorrect Press");
                    }
                });
            }
        });
    }

    //Derived from from 301 lab
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
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

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(statistics, out);
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
