package fr.mhyran.spacequizz2.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import fr.mhyran.spacequizz2.Config.Constant;
import fr.mhyran.spacequizz2.Database.AppDatabase;
import fr.mhyran.spacequizz2.Entity.User;
import fr.mhyran.spacequizz2.Interfaces.UserDao;
import fr.mhyran.spacequizz2.R;


public class HighscoreActivity extends AppCompatActivity {

    AppDatabase db;
    private List<User> listUsers;
    ListView lvUser;

    private TextView tvScoreHS;

    String hspseudotv;
    Long hsidtv;
    int scorebd;

    @Override
    public void onBackPressed() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Intent myIntent = getIntent();
        int score = myIntent.getIntExtra("scoreHS", 0);
        hspseudotv = myIntent.getStringExtra("pseudo");
        hsidtv = getIntent().getLongExtra("id", 0);
        tvScoreHS = findViewById(R.id.textView2);

        tvScoreHS.setText(hspseudotv+" Score: "+String.valueOf(score));



        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constant.BD_NAME)
                .allowMainThreadQueries()
                .build();


       int scorebd = db.userDao().scoredb(hspseudotv);
       System.out.println("scorebd "+scorebd);

        if (scorebd<=score) {
            db.userDao().updateScore(hspseudotv, score);
        }

        //obtengo datos de mi table
        listUsers = db.userDao().getAllUsers();
        lvUser = (ListView) findViewById(R.id.lvUser);

        AdapterUser adapterUser = new AdapterUser(this);
        lvUser.setAdapter(adapterUser);



        Button menuButton = (Button) findViewById(R.id.menubtn);
        Button restartButton = (Button) findViewById(R.id.restartbtn);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(HighscoreActivity.this, MainActivity.class);
                startActivity(menuIntent);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);;
            }
        });

    }

    class AdapterUser extends ArrayAdapter<User> {
            AppCompatActivity appCompatActivity;

            public AdapterUser(AppCompatActivity context){
                super(context, R.layout.item_userscore, listUsers);
                appCompatActivity = context;
            }
            public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_userscore, null);

            TextView txtname = (TextView)item.findViewById(R.id.txtItemName);
            String dato = listUsers.get(position).getId() + " - " + listUsers.get(position).getPseudo() + " : " + listUsers.get(position).getScore();
            txtname.setText(dato);

            return item;
            }
    }
}




