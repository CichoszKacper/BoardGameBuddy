package com.boardgamebuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

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


                //Intent startIntent = new Intent(getApplicationContext(), GameHistoryActivity.class);//make new intent which launches the .xml file you want
                //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
                //startActivity(startIntent);//start the activity
            }

        });

    }
}
