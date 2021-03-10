package fr.mhyran.spacequizz2.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.*;

import fr.mhyran.spacequizz2.Config.Constant;
import fr.mhyran.spacequizz2.Database.AppDatabase;
import fr.mhyran.spacequizz2.R;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constant.BD_NAME)
                .allowMainThreadQueries()
                .build();
        int quantity = db.userDao().count();
        //Toast.makeText(this, "Enregistrements: " + quantity, Toast.LENGTH_SHORT).show();


       // int delete = db.userDao().deleteById(1);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        Button playButton = (Button) findViewById(R.id.playButton);
        ImageButton exit = (ImageButton) findViewById(R.id.exit);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginActivity);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exit = new Intent(Intent.ACTION_MAIN);
                exit.addCategory(Intent.CATEGORY_HOME);
                exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(exit);
            }
        });

    }

}