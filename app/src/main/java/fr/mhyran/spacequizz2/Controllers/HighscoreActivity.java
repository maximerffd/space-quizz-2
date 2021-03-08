package fr.mhyran.spacequizz2.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
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
import fr.mhyran.spacequizz2.R;


public class HighscoreActivity extends AppCompatActivity {

    AppDatabase db;
    private List<User> listUsers;
    ListView lvUser;

    private TextView tvScoreHS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Intent mIntent = getIntent();
        int score = mIntent.getIntExtra("scoreHS", 0);

        tvScoreHS = findViewById(R.id.textView2);

        tvScoreHS.setText("Score: "+String.valueOf(score));


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, Constant.BD_NAME)
                .allowMainThreadQueries()
                .build();
        //obtengo datos de mi table
        listUsers = db.userDao().getAllUsers();

        lvUser = (ListView) findViewById(R.id.lvUser);

        AdapterUser adapterUser = new AdapterUser(this);
        lvUser.setAdapter(adapterUser);


        Button exitButton = (Button) findViewById(R.id.exitbtn);
        Button restartButton = (Button) findViewById(R.id.restartbtn);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitActivity = new Intent(HighscoreActivity.this, MainActivity.class);
                startActivity(exitActivity);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartActivity = new Intent(HighscoreActivity.this, GameActivity.class);
                startActivity(restartActivity);
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
            String dato = listUsers.get(position).getId() + " - " + listUsers.get(position).getPseudo() + " - " + listUsers.get(position).getScore();
            txtname.setText(dato);

            return item;
            }
    }
}




