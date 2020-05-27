package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "prefs";
    private  int minNumber = 0;
    private  int maxNumber;

    private int scoreZ;

    private  int randomNumber;

    private int maxTurns;
    private int currentTurn = 0;

    private int result = 0;

    private TextView numberRangeText;
    private TextView availableScore;
    private TextView resultText;
    private TextView turnsText; //ejimu skc

    private EditText numberField; //irasomas skaiciukas(spejimas)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        maxTurns = setMaxTurns();
        maxNumber = setMaxNumber();

        numberRangeText = findViewById(R.id.number_range_txt);
        availableScore = findViewById(R.id.available_score);//(R.id.playerName);
        resultText = findViewById(R.id.result_txt);
        turnsText = findViewById(R.id.turns_txt);
        numberField = findViewById(R.id.number_field);
        updateTexts(0);
        Random random = new Random();
        randomNumber = random.nextInt(maxNumber - minNumber) + minNumber; //kad gauti random numeri tarp reziu
    }

    private void updateTexts(int guessNumber){
        scoreZ = (maxTurns - currentTurn) * maxNumber;
        numberRangeText.setText(String.format(getResources().getString(R.string.number_range_format), minNumber, maxNumber));//is strings failo paima reiksme
        availableScore.setText(String.format(getResources().getString(R.string.score_format), scoreZ));
        if (result == -1){
            //per didelis
            resultText.setText(String.format(getResources().getString(R.string.result_format), guessNumber, getResources().getString(R.string.result_high)));
        }
        else if (result == 1){
            //per mazas
            resultText.setText(String.format(getResources().getString(R.string.result_format), guessNumber , getResources().getString(R.string.result_low)));
        }
        else if (result == 2){
            resultText.setText(String.format(getResources().getString(R.string.result_out_of_range), guessNumber));
        }
        turnsText.setText(String.format(getResources().getString(R.string.turns_format), currentTurn, maxTurns));//currentTurn ir maxTurns isiraso i stringa
    }

    public  void  guessClick(View view)
    {
        currentTurn++; //atliktas vienas ejimas

        int guessNumber = Integer.parseInt(numberField.getText().toString()); //pasiverciam ivesta skaiciu is stringo i int
        if (randomNumber < guessNumber){
            result = -1;
        }

        else if(randomNumber > guessNumber){
            if (guessNumber > maxNumber) {
                result = 2;
            }
            result = 1;
        }

        //jeigu atspejam, result islieka 0
        else
            result = 0;
        if (currentTurn >= maxTurns && result !=0){
            //Lose
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("guessedNumber", guessNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("win", false);
            startActivity(intent);
            finish();
        }
        else if(result == 0){
            //Win

            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("guessedNumber", guessNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("win", true);
            intent.putExtra("score", scoreZ);
            startActivity(intent);
            finish();
        }
        updateTexts(guessNumber);
    }

    private int setMaxTurns(){
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        int diff = prefs.getInt("difficulty", 0);
        switch(diff)
        {
            case 0:
                return 15;
            case 1:
                return 10;
            case 2:
                return 7;
            case 3:
                return 2;
        }
        return 0;
    }

    private int setMaxNumber(){
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        int diff = prefs.getInt("difficulty", 2);
        switch(diff)
        {
            case 0:
                return 25;
            case 1:
                return 50;
            case 2:
                return 100;
            case 3:
                return 100;
        }
        return 0;
    }

    public void aboutBackClick(View view)
    {
        finish();
    }

}
