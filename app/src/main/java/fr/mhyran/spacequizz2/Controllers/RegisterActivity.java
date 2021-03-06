package fr.mhyran.spacequizz2.Controllers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import fr.mhyran.spacequizz2.Config.Constant;
import fr.mhyran.spacequizz2.Database.AppDatabase;
import fr.mhyran.spacequizz2.Entity.User;
import fr.mhyran.spacequizz2.Interfaces.UserDao;
import fr.mhyran.spacequizz2.R;


public class RegisterActivity extends AppCompatActivity {
    List<User> listUsernames;
    boolean pseudoAlreadyUsed = false;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        EditText username = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        listUsernames = new ArrayList<>();


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constant.BD_NAME)
                .allowMainThreadQueries()
                .build();


        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User obj = new User();
                obj.setPseudo(username.getText().toString().trim().toLowerCase());
                obj.setPassword(password.getText().toString().trim());
                if (validateInput(obj)) {
                    AppDatabase sInstance = AppDatabase.getAppDatabase(getApplicationContext());
                    UserDao userDao = sInstance.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            pseudoAlreadyUsed = false;
                            listUsernames.addAll(userDao.getAllUsersReg());
                            System.out.println(userDao.getAllUsersReg());
                            for (int i = 0; i < listUsernames.size(); i++) {
                                if (obj.getPseudo().equals(listUsernames.get(i).getPseudo())) {
                                    pseudoAlreadyUsed = true;
                                }
                            };
                            if (pseudoAlreadyUsed) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Pseudo d??j?? utilis??", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                userDao.registerUser(obj);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Utilisateur enregistr??", Toast.LENGTH_SHORT).show();
                                        Intent mainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(mainActivity);
                                    }
                                });
                            }
                        }
                    }).start();
                } else {
                    Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateInput(User obj) {
        return !obj.getPseudo().isEmpty() &&
                !obj.getPassword().isEmpty();
    }
}



