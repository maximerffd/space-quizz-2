package fr.mhyran.spacequizz2.Controllers;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import fr.mhyran.spacequizz2.Models.Question;
import fr.mhyran.spacequizz2.R;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private TextView tvQuestion, tvScore, tvUserName;
    private Button btn1, btn2, btn3, btn4, btnNext;

    int totalQuestions;
    int qCounter=0;
    int lifePoints = 3;
    int score = 0;


    boolean checkAns1 = false;
    boolean checkAns2 = false;
    boolean checkAns3 = false;
    boolean checkAns4 = false;
    ColorStateList dfbtnColor;

    String gapseudotv;
    Long gaidtv;

    private Question currentQuestion;
    private List<Question> questionsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        questionsList= new ArrayList<>();
        tvQuestion=findViewById(R.id.activity_game_question_text);
        tvScore=findViewById(R.id.activity_game_info_score);

       gapseudotv = getIntent().getStringExtra("pseudotv");
       gaidtv = getIntent().getLongExtra("idtv", 0);

        tvUserName=findViewById(R.id.activity_game_info_player);
        tvUserName.setText(gaidtv + " " + gapseudotv);



        btn1=findViewById(R.id.activity_game_answer1_btn);
        btn2=findViewById(R.id.activity_game_answer2_btn);
        btn3=findViewById(R.id.activity_game_answer3_btn);
        btn4=findViewById(R.id.activity_game_answer4_btn);
        btnNext=findViewById(R.id.activity_game_Next);

        dfbtnColor = btn1.getTextColors();

        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();


        View.OnClickListener clickOnBtn1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1, btn1);
                checkAns1 = true;
            }
        };
        View.OnClickListener clickOnBtn2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2, btn2);
                checkAns2 = true;
            }
        };
        View.OnClickListener clickOnBtn3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3, btn3);
                checkAns3 = true;
            }
        };
        View.OnClickListener clickOnBtn4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(4, btn4);
                checkAns4 = true;
            }
        };

        btn1.setOnClickListener(clickOnBtn1);
        btn2.setOnClickListener(clickOnBtn2);
        btn3.setOnClickListener(clickOnBtn3);
        btn4.setOnClickListener(clickOnBtn4);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(checkAns1==true || checkAns2==true || checkAns3==true || checkAns4==true){
                        showNextQuestion();
                    }
                    else{
                        Toast.makeText(GameActivity.this, "Sélectionnez une option", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    private void checkAnswer(int userAnswer, Button btnPressed) {


            if (userAnswer == currentQuestion.getCorrectAnsNo()){
                btnPressed.setTextColor(Color.GREEN);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);

                score+=currentQuestion.getValueQA();
                tvScore.setText("Score: "+String.valueOf(score));


            }else{
                btnPressed.setTextColor(Color.RED);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                lifePoints-=1;
                score-=currentQuestion.getValueQA();
                tvScore.setText("Score: "+String.valueOf(score));


                if(lifePoints==2){
                    findViewById(R.id.life2).setVisibility(View.GONE);
                }
                if(lifePoints==1){
                    findViewById(R.id.life2).setVisibility(View.GONE);
                    findViewById(R.id.life3).setVisibility(View.GONE);
                }

                if(lifePoints==0){

                    gapseudotv = getIntent().getStringExtra("pseudotv");
                    gaidtv = getIntent().getLongExtra("idtv", 0);


                    Intent myIntent = new Intent(GameActivity.this, HighscoreActivity.class);
                    myIntent.putExtra("scoreHS", score);
                    myIntent.putExtra("pseudo", gapseudotv);
                    myIntent.putExtra("id", gaidtv);
                    startActivity(myIntent);

                }
            }


            if(qCounter < totalQuestions){
                btnNext.setText("Next");
            }else{
                btnNext.setText("The End");
            }

    }


    private void showNextQuestion() {

        btn1.setTextColor(dfbtnColor);
        btn2.setTextColor(dfbtnColor);
        btn3.setTextColor(dfbtnColor);
        btn4.setTextColor(dfbtnColor);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        checkAns1 = false;
        checkAns2 = false;
        checkAns3 = false;
        checkAns4 = false;




        if(qCounter < totalQuestions){

            currentQuestion = questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestionText());
            btn1.setText(currentQuestion.getOption1());
            btn2.setText(currentQuestion.getOption2());
            btn3.setText(currentQuestion.getOption3());
            btn4.setText(currentQuestion.getOption4());

            qCounter++;


        }else{
            gapseudotv = getIntent().getStringExtra("pseudotv");
            gaidtv = getIntent().getLongExtra("idtv", 0);
            Intent myIntent = new Intent(GameActivity.this, HighscoreActivity.class);
            myIntent.putExtra("scoreHS", score);
            myIntent.putExtra("pseudo", gapseudotv);
            myIntent.putExtra("id", gaidtv);
            startActivity(myIntent);
        }


        }



    private void addQuestions() {
        questionsList.add(new Question("Combien de compartiments comporte l'estomac de la vache?", "2", "1", "3", "4", 4, 20));
        questionsList.add(new Question("Quel est le premier film Disney?", "Cendrillon", "Blanche-Neige et les sept nains", "La belle au bois dormant", "Bambi", 2, 15));
        questionsList.add(new Question("Qui a peint \"La cène\"?", "Edvard Munch", "Vincent van Gogh", "Leonardo Da Vinci", "Pablo Picasso", 3, 15));
        questionsList.add(new Question("Qui est la planète la plus proche du soleil?", "Mars", "Mercure", "Vénus", "Terre", 2, 15));
        questionsList.add(new Question("Qui est l'auteur de l'Odyssée?", "Homére", "Shakespeare", "Cervantes", "June Austen", 1, 20));
        questionsList.add(new Question("Quelle est la durée d'un match du football?", "45 minutes", "60 minutes", "90 minutes", "30 minutes", 3, 10));
        questionsList.add(new Question("Quelle est la nationalité de Diego Maradona?", "italienne", "brésilienne", "argentine", "portugaise", 3, 15));
        questionsList.add(new Question("Que veut dire FTP?", "File Transfer Protocol", "File Transmission Protocol", "Fiber Transfer Password", "Fiber Transfer Protocol", 1, 10));
        questionsList.add(new Question("Combien de temps dure la grossesse d'un éléphant?", "12 mois", "18 mois", "22 mois", "9 mois", 3, 15));
        questionsList.add(new Question("De quel pays vient la \"caipirinha\"?", "Cuba", "Brésil", "Mexique", "Costa Rica", 2, 15));
        questionsList.add(new Question("Quel est le gaz principal dans l'atmosphére?", "Dioxygéne", "Dioxyde de carbone", "Ozone", "Diazote", 4, 20));
        questionsList.add(new Question("Quel type de biere est la \"guinness\"?", "IPA", "Lager", "Stout", "Pale ale", 3, 10));
        questionsList.add(new Question("Quel est l'ingrédient principal de la Tequila?", "Agave", "Cactus", "Canne à sucre", "Malt", 1, 15));
        questionsList.add(new Question("Quel est le pays le plus gayfriendly au monde?", "Argentine", "Russie", "Brésil", "Pays-bas", 4, 10));
        questionsList.add(new Question("Qui est l'inventeur de l'ampoule électrique?", "Nikola Tesla", "Thomas Alva Edison", "Les fréres lumiére", "Alexander Graham Bell", 2, 15));
        questionsList.add(new Question("Quel est l'océan le plus grand ?", "L'océan Atlantique", "L'océan Pacifique", "L'océan Arctique", "L'océan Austral", 2, 15));
        questionsList.add(new Question("Qui est l'animal le plus ancien ?", "La méduse", "La fourmi de Mars", "Le requin-lézard", "La limule", 1, 20));
        questionsList.add(new Question("Qui est l'inventeur du lave glace ?", "André Citroën", "Karl Benz", "Mary Anderson", "Nikola Tesla", 3, 20));
        questionsList.add(new Question("Combien d'hectares de couverture végétale ont été perdus en Amazonie entre 1985 et 2018 ?", "32 millions", "22 millions", "42 millions", "72 millions", 4, 15));
        questionsList.add(new Question("Dates de la Première Guerre mondiale", "28-06-1914/ 10-11-1918", "28-07-1914/ 11-11-1918", "28-07-1914/ 10-11-1918", "28-06-1914/ 11-11-1918", 2, 20));
        questionsList.add(new Question("Le rhinocéros noir occidental a été déclaré éteint en 2011. Pourquoi cette espèce est-elle braconnée?", "Sa corne", "Sa graisse", "Securité locale", "Sa viande ", 1, 10));
        questionsList.add(new Question("Premier roi de France", "Charles I", "Clovis I", "Clodomir", "Thibaut", 2, 15));
        questionsList.add(new Question("CCXLI + LXXXV", "156", "256", "326", "196", 3, 25));
        questionsList.add(new Question("DCCCLXXVII", "437", "877", "527", "637", 2, 25));
        questionsList.add(new Question("En quelle année a eu lieu le premier vote féminin en France?", "1944", "1945", "1845", "1930", 1, 20));
        questionsList.add(new Question("Quel est le plus long fleuve du monde?", "Le Nil", "Le Mississipi", "Le Yangzi Jiang", "L'Amazone", 4, 20));
        questionsList.add(new Question("Qu'est-ce qu'un octet?", "1 bit", "1 byte", "8 bytes", "Aucune de ces réponses", 2, 20));
        questionsList.add(new Question("Que signifie l'acronyme CPU?", "Central Protection Unit", "Central Processing Unit", "Central Personal Unit", "Aucune de ces réponses", 2, 15));
        questionsList.add(new Question("Qui a peint \"Le Cri\"?", "Edvard Munch", "Vincent van Gogh", "Leonardo Da Vinci", "Pablo Picasso", 1, 20));
        questionsList.add(new Question("Quelle est la nationalité de Vincent van Gogh?", "italienne", "néerlandaise", "francaise", "espagnole", 2, 15));
        questionsList.add(new Question("Combien y a-t-il de planétes dans le système solaire?", "9", "11", "8", "7", 3, 20));
        questionsList.add(new Question("Quelles sont les principales langues parlées en Afghanistan?", "Arabe et turc", "Hindi et persan", "Afghan et anglais", "Persan et pashto", 4, 25));
        questionsList.add(new Question("Quelle est la capitale du Paraguay?", "Encarnacion", "Asuncion", "Buenos Aires", "La paz", 2, 20));
        questionsList.add(new Question("Quelle est la prochaine lettre de cette suite?Q-E-L-P-L-D-C-...", "Q", "L", "D", "S", 4, 15));
        questionsList.add(new Question("Quel est le prochain numéro de cette suite? 1-5-10-14-28...", "48", "32", "34", "56", 2, 20));
        questionsList.add(new Question("Qui est le createur de Star Wars?", "George Lucas", "Matt Groening", "Stan Lee", "Mark Zuckerberg", 1, 10));
        questionsList.add(new Question("Qui est le premier super héro de Marvel?", "Captain America", "Iron Man", "Hulk", "Torche humaine", 4, 20));
        questionsList.add(new Question("Completez la suite suivante 3-13-1113-3113-...", "211313", "132113", "212213", "131223", 2, 25));
        questionsList.add(new Question("Quel est le pays le plus grand du monde?", "Brésil", "Russie", "États-Unis", "Canada", 2, 10));
        questionsList.add(new Question("Quel est l'animal le plus intelligent du monde?", "Le chimpanzé", "Le perroquet", "Le dauphin", "Le cochon", 1, 15));
        questionsList.add(new Question("Quel est le nombre de joueurs dans une équipe de basket sur le terrain?", "11", "6", "7", "5", 4, 15));


    }

}

