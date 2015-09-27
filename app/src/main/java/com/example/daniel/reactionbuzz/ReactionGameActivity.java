package com.example.daniel.reactionbuzz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class ReactionGameActivity extends AppCompatActivity {

    Button reactionbutton;
    public ButtonPress reactiontime = new ButtonPress();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reactiongame);

        startActivity(new Intent(ReactionGameActivity.this, InfoPop.class));

        final Handler handler = new Handler();

        Random rand = new Random();
        final int randomNum = rand.nextInt(20);

        reactionbutton = (Button)findViewById(R.id.reactionbutton);
        reactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // waiting 10 ms
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 10);

                reactiontime.onPress(reactionbutton);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms*randomNum
                        reactiontime.onReset(reactionbutton);
                        final long startTime = System.nanoTime();
                        reactionbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                long endTime = System.nanoTime() - startTime;
                                Toast.makeText(ReactionGameActivity.this, String.format("Time: %dms", endTime / 1000000), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, 100 * randomNum);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_game, menu);
        return true;
    }

    public void reactionstats(MenuItem menu) {
        Toast.makeText(this, "Reaction Stats", Toast.LENGTH_SHORT).show();

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
}
