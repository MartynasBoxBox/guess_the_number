package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int m_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGameBtn = findViewById(R.id.start_game_btn);
        Button resultsBtn = findViewById(R.id.results_btn);
        Button settingsBtn = findViewById(R.id.settings_btn);
        Button aboutBtn = findViewById(R.id.about_btn);
    }

    public void startClick(View view) {
        //keicia header reiksme pagal clickus


        if (view == findViewById(R.id.start_game_btn)) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } else if (view == findViewById(R.id.results_btn)) {
            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        } else if (view == findViewById(R.id.settings_btn)) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (view == findViewById(R.id.about_btn)) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
    }
}
