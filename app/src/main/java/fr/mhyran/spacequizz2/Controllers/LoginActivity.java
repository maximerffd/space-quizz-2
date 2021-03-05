package fr.mhyran.spacequizz2.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.mhyran.spacequizz2.R;
import fr.mhyran.spacequizz2.utilities.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        databaseHelper = new DatabaseHelper(this);

        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (databaseHelper.checkUser(username.getText().toString().trim()
                        , password.getText().toString().trim())) {
                    Intent accountsIntent = new Intent(LoginActivity.this, GameActivity.class);
                    accountsIntent.putExtra("Username", username.getText().toString().trim());
                    startActivity(accountsIntent);
                    username.setText(null);
                    password.setText(null);




                } else {
                    // Snack Bar to show success message that record is wrong
                    Toast.makeText(LoginActivity.this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }




                //Intent gameActivity = new Intent(LoginActivity.this, GameActivity.class);
                //startActivity(gameActivity);
            }
        });
    }

}