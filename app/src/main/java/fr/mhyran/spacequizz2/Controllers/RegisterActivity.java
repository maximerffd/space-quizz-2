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

import fr.mhyran.spacequizz2.Models.User;
import fr.mhyran.spacequizz2.R;
import fr.mhyran.spacequizz2.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class  RegisterActivity extends AppCompatActivity {
    List<User> listUsers;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    boolean usernameIsFilled = false;
    boolean passwordIsFilled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        User user = new User();
        listUsers = new ArrayList<>();


        EditText username = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setEnabled(false);

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




        registerButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                user.setName(username.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                databaseHelper.addUser(user);
                getDataFromSQLite();
                System.out.println("ID : " + user.getId());
                System.out.println("Pseudo : " + user.getName());
                System.out.println("Password : " + user.getPassword());
                Toast.makeText(RegisterActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();


                Intent loginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginActivity);
            }
        });


    }

    private void getDataFromSQLite() {
        listUsers.addAll(databaseHelper.getAllUser());
        System.out.println("List User : " + listUsers);
        for(int i = 0; i < listUsers.size(); i++){
            System.out.println( "User id in list " + listUsers.get(i).getId() +  listUsers.get(i).getName());
        }
    }
}