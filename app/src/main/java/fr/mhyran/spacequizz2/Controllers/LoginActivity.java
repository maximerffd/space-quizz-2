package fr.mhyran.spacequizz2.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import fr.mhyran.spacequizz2.Config.Constant;
import fr.mhyran.spacequizz2.Database.AppDatabase;
import fr.mhyran.spacequizz2.Entity.User;
import fr.mhyran.spacequizz2.Interfaces.UserDao;
import fr.mhyran.spacequizz2.R;


public class LoginActivity extends AppCompatActivity {

AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constant.BD_NAME)
                .allowMainThreadQueries()
                .build();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pseudoText= username.getText().toString();
                String passwordText= password.getText().toString();
                if(pseudoText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                }else {
                    AppDatabase sInstance = AppDatabase.getAppDatabase(getApplicationContext());
                    UserDao userDao = sInstance.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User obj = userDao.login(pseudoText, passwordText);
                            if(obj == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else{
                                String pseudo = obj.getPseudo();
                                Long id = obj.getId();
                                startActivity(new Intent (LoginActivity.this, GameActivity.class)
                                        .putExtra("pseudotv", pseudo)
                                        .putExtra("idtv", id));
                            }

                            }

                    }).start();
                }
            }
        });
    }

}

  /*if (databaseHelper.checkUser(username.getText().toString().trim()
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
             */

