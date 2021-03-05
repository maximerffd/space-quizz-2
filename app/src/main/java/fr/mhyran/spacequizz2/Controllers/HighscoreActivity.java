package fr.mhyran.spacequizz2.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import fr.mhyran.spacequizz2.R;
import fr.mhyran.spacequizz2.utilities.DatabaseHelper;

public class HighscoreActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    private TextView tvScoreHS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        
        Intent mIntent = getIntent();
        int score = mIntent.getIntExtra("scoreHS", 0);

        tvScoreHS = findViewById(R.id.textView2);

        tvScoreHS.setText("Score: "+String.valueOf(score));


        Button quitButton = (Button) findViewById(R.id.quitButton);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    System.exit(0);

                }
        });
    }
}

