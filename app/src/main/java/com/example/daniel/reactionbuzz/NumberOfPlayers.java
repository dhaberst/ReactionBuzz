package com.example.daniel.reactionbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NumberOfPlayers extends AppCompatActivity {

    Button twoplayer;
    Button threeplayer;
    Button fourplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numberofplayers);

        twoplayer = (Button)findViewById(R.id.twoplayers);
        twoplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumberOfPlayers.this, TwoPlayerGame.class);
                startActivity(intent);
            }
        });

        threeplayer = (Button)findViewById(R.id.threeplayers);
        threeplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumberOfPlayers.this, ThreePlayerGame.class);
                startActivity(intent);
            }
        });

        fourplayer = (Button)findViewById(R.id.fourplayers);
        fourplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumberOfPlayers.this, FourPlayerGame.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_number_of_players, menu);
        return true;
    }

    public void buzzerstats(MenuItem menu) {
        Intent intent = new Intent(NumberOfPlayers.this, BuzzerStatsActivity.class);
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
}
