package fr.mhyran.spacequizz2.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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




public class  RegisterActivity extends AppCompatActivity {


    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        EditText username = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        Button registerButton = (Button) findViewById(R.id.registerButton);


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constant.BD_NAME)
                .allowMainThreadQueries()
                .build();


        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User obj = new User();
                obj.setPseudo(username.getText().toString());
                obj.setPassword(password.getText().toString());
             if (validateInput(obj)) {
                 AppDatabase sInstance = AppDatabase.getAppDatabase(getApplicationContext());
                 UserDao userDao = sInstance.userDao();
                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         userDao.registerUser(obj);
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                 Intent mainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                                 startActivity(mainActivity);
                             }
                         });
                     }
                 }).start();
             }else{
                 Toast.makeText(RegisterActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
    private Boolean validateInput(User obj) {
        if (obj.getPseudo().isEmpty() ||
        obj.getPassword().isEmpty()){
            return false;
        }
        return true;
    }
}

                //insertar en la base de datos por DAO

               /* long result = db.userDao().insert(obj);
                if (result>0){
                    //correcto
                    Intent mainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(mainActivity);
                }else{
                    Toast.makeText(RegisterActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
                }

            }

        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().equals("")){
                    registerButton.setEnabled(false);
                    usernameIsFilled = false;
                }else {
                    usernameIsFilled = true;
                    if( usernameIsFilled == true && passwordIsFilled == true) {
                        registerButton.setEnabled(true);
                    }
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    registerButton.setEnabled(false);
                    usernameIsFilled = false;
                }else {
                    usernameIsFilled = true;
                    if( usernameIsFilled == true && passwordIsFilled == true) {
                        registerButton.setEnabled(true);
                    }
                }

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().equals("")){
                    registerButton.setEnabled(false);
                    passwordIsFilled = false;
                }else {
                    passwordIsFilled = true;
                    if( usernameIsFilled == true && passwordIsFilled == true) {
                        registerButton.setEnabled(true);
                    }
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    registerButton.setEnabled(false);
                    passwordIsFilled = false;
                }else {
                    passwordIsFilled = true;
                    if( usernameIsFilled == true && passwordIsFilled == true) {
                        registerButton.setEnabled(true);
                    }
                }
            }
        });
    }
}*/




