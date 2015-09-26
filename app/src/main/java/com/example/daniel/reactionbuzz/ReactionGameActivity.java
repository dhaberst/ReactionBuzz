package com.example.daniel.reactionbuzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class ReactionGameActivity extends AppCompatActivity {

    Button reactionbutton;
    public ButtonPress reactiontime = new ButtonPress();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final  Timer timer = new Timer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reactiongame);

        reactionbutton = (Button)findViewById(R.id.reactionbutton);
        reactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // waiting 10 ms
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                reactiontime.onPress(reactionbutton);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Code
                    }
                }, 2000);

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
