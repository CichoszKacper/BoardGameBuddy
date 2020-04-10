package com.boardgamebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateLogInBtn();
        activateRegisterBtn();
        activateNewGameBtn();
        activateDiceRollerBtn();
        activateScoreTrackerBtn();
        activatePlayerSelectorBtn();
        activateGameHistoryBtn();

    }

    public void activateLogInBtn(){
        Button logInActivityBtn = (Button) findViewById(R.id.logInBtn);

        logInActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                Intent startIntent = new Intent(getApplicationContext(), LogInActivity.class);//make new intent which launches the .xml file you want
                //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
                startActivity(startIntent);//start the activity
            }
        });
    }

    public void activateRegisterBtn(){
        Button registerActivityBtn = (Button) findViewById(R.id.registerBtn);

        registerActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                Intent startIntent = new Intent(getApplicationContext(), RegisterActivity.class);//make new intent which launches the .xml file you want
                //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
                startActivity(startIntent);//start the activity
            }
        });
    }

    public void activateNewGameBtn(){
        Button newGameActivityBtn = (Button) findViewById(R.id.newGameBtn);

        newGameActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                Intent startIntent = new Intent(getApplicationContext(), NewGameActivity.class);//make new intent which launches the .xml file you want
                //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
                startActivity(startIntent);//start the activity
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

    public void activateScoreTrackerBtn(){
        Button scoreTrackerActivityBtn = (Button) findViewById(R.id.scoreTrackerBtn);

        scoreTrackerActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                Intent startIntent = new Intent(getApplicationContext(), ScoreTrackerActivity.class);//make new intent which launches the .xml file you want
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

    public void activateGameHistoryBtn(){
        Button gameHistoryActivityBtn = (Button) findViewById(R.id.gameHistoryBtn);

        gameHistoryActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                Intent startIntent = new Intent(getApplicationContext(), GameHistoryActivity.class);//make new intent which launches the .xml file you want
                //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
                startActivity(startIntent);//start the activity
            }
        });
    }


}
