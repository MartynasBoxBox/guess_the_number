package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameOverActivity extends AppCompatActivity {

    private TextView winText;
    private ImageView imagePic;
    private int scoreZ;
    private static final String PREFS_FILE = "Prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        winText = findViewById(R.id.win_txt);
        imagePic = findViewById(R.id.imageView6);
        Intent intent = getIntent();//is kito activity paima

        boolean win = intent.getBooleanExtra("win", false);//raktas win toks pat turi but pagal nutylejima reiksme false
        if (win){
            winText.setText(getResources().getString(R.string.text_win));
            imagePic.setImageResource(R.drawable.win);
            addDataToResults();
        }
        else{
            winText.setText(getResources().getString(R.string.text_lose));
            imagePic.setImageResource(R.drawable.rip);
        }
    }

    private void addDataToResults()
    {
        ResultsDatabaseHandler dbhandler = new ResultsDatabaseHandler(this);
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        Intent intent = getIntent();
        scoreZ = intent.getIntExtra("score", 0);
        String name = prefs.getString("playerName", "Name");
        int age = scoreZ;
        int diff = prefs.getInt("difficulty", 0);
        String difficulty = "";
        switch(diff)
        {
            case 0:
                difficulty = getResources().getStringArray(R.array.difficulty_items)[0];
                break;
            case 1:
                difficulty = getResources().getStringArray(R.array.difficulty_items)[1];
                break;
            case 2:
                difficulty = getResources().getStringArray(R.array.difficulty_items)[2];
                break;
            case 3:
                difficulty = getResources().getStringArray(R.array.difficulty_items)[3];
                break;
        }

        dbhandler.addData(new PersonData(0, name, difficulty, age));

    }
    public void aboutBackClick(View view)
    {
        finish();
    }
}
