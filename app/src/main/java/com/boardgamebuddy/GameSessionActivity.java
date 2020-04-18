package com.boardgamebuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameSessionActivity extends AppCompatActivity {

    private GameSessionViewModel mSessionViewModel;
    private TextView playerCount;
    private TextView gameTitle;
    private TextView score;
    private long startTime;
    private long endTime;
    private int numOfPlayers;
    private String title;
    private Switch mWon;
    private Button plusBtn;
    private Button minusBtn;

    private TextView minutesView;
    boolean timerPassed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);

        mSessionViewModel = new ViewModelProvider(this).get(GameSessionViewModel.class);
        startTime = System.currentTimeMillis();

        playerCount = findViewById(R.id.playerCount);
        gameTitle = findViewById(R.id.gameTitleTextView);
        score = findViewById(R.id.currentScoreText);
        mWon = findViewById(R.id.wonSwitch);
        minutesView = findViewById(R.id.minutesView);
        timerPassed = getIntent().getBooleanExtra("timerSelected", false);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);


        minutesView.setVisibility(EditText.GONE);

        numOfPlayers = getIntent().getIntExtra("playerCount", 0);
        if(numOfPlayers == -1){
            playerCount.setVisibility(EditText.GONE);
        } else {
            playerCount.setText("Player Count: " + numOfPlayers);
        }
         title = getIntent().getStringExtra("title");
        gameTitle.setText("Game: " + title);
        score.setText(Integer.toString(getIntent().getIntExtra("score", 0)));

        activateFinishBtn();
        long miliseconds = 1000;
        if(timerPassed){
            minutesView.setVisibility(EditText.VISIBLE);
            int seconds = (getIntent().getIntExtra("timerSeconds", 0)) * 1000;
            miliseconds = seconds;
        }

        new CountDownTimer(miliseconds, 1000) {

            public void onTick(long millisUntilFinished) {
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);

                long remainder = (millisUntilFinished / 1000) % 60;
                // long seconds = (milliseconds / 1000);
                long seconds1 = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                long seconds2 = seconds1 % 60;
                minutesView.setText("Time remaining: " + minutes + ":" + seconds2 );
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                minutesView.setText("Timer finished!");
            }

        }.start();

        activateDiceRollerBtn();
        activatePlayerSelectorBtn();
        plusBtnClick();
        minusBtnClick();
    }

    public void activateFinishBtn(){
        Button finishBtn = (Button) findViewById(R.id.finishSessionBtn);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event


                endTime = System.currentTimeMillis();

                long duration = (endTime - startTime);
                int totalDuration = (int) TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
                String profile = "sampleProf";
                //String title = gameTitle.getText().toString();
                //int numOfPlayers = getIntent().getIntExtra("playerCount", 0);
                int endScore = Integer.parseInt(score.getText().toString());

                Date currentDate = new Date();

                boolean won;
                if(mWon.isChecked()){//POTENTIAL ISSUE
                    won = true;
                } else {
                    won = false;
                }

                //GameSession newSession = new GameSession(data.getStringExtra("profile"), data.getStringExtra("game"), data.getIntExtra("numOfPlayers", 0), data.getIntExtra("score", 0), data.getBooleanExtra("won", false), currentDate, data.getIntExtra("duration", 0));

                GameSession newSession = new GameSession(profile, title, numOfPlayers, endScore, won, currentDate, totalDuration);

                mSessionViewModel.insert(newSession);

                /*Date date = new Date();
                //Date testDate2 = new Date(2019, 03, 03);



                GameSession session = new GameSession("TestMember", "Gametitle1", 3, 10, true, date, 25);
                dao.insert(session);*/


                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);//make new intent which launches the .xml file you want

                startActivity(startIntent);//start the activity

                Toast.makeText(GameSessionActivity.this, "Game session saved successfully", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void activateDiceRollerBtn(){
        Button diceRollerActivityBtn = (Button) findViewById(R.id.diceRollerBtn);

        diceRollerActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                Intent startIntent = new Intent(getApplicationContext(), DiceRollerActivity.class);//make new intent which launches the .xml file you want
                //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
                startActivity(startIntent);//start the activity
            }
        });
    }

    public void activatePlayerSelectorBtn(){
        Button playerSelectorActivityBtn = (Button) findViewById(R.id.playerSelectorBtn);

        playerSelectorActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                Intent startIntent = new Intent(getApplicationContext(), PlayerSelectorActivity.class);//make new intent which launches the .xml file you want
                //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
                startActivity(startIntent);//start the activity
            }
        });
    }

    public void plusBtnClick(){
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                int current = Integer.parseInt(score.getText().toString());
                current++;
                //update display
                score.setText("" + current);
            }
        });

    }

    public void minusBtnClick(){
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                int current = Integer.parseInt(score.getText().toString());
                current--;
                //update display
                score.setText("" + current);
            }
        });

    }

}
