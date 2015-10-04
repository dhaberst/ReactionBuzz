package com.ualberta.daniel.reactionbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class ReactionStatsActivity extends AppCompatActivity {

    ListView listView;
    TextView infoView;
    Button clear;

    private static final String FILENAME = "reactStat.sav";

    private ArrayAdapter<Long> reactionAdapter;
    private StatsReaction statistics = new StatsReaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reactionstats);

        loadFromFile();

        listView = (ListView) findViewById(R.id.statsView);
        clear = (Button)findViewById(R.id.clearbutton);
        infoView = (TextView)findViewById(R.id.InfoView);

        reactionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, statistics.returnList());
        listView.setAdapter(reactionAdapter);

        infoView.setText(statistics.getTextView());

        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                statistics.clear();
                saveInFile();
                reactionAdapter.notifyDataSetChanged();
                infoView.setText(statistics.getTextView());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_stats, menu);
        return true;
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

    public void emailstats(MenuItem menu) {
        Intent intent = new Intent(ReactionStatsActivity.this, EmailPop.class);
        startActivity(intent);

    }

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
