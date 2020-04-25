package com.boardgamebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton profileButton = findViewById(R.id.profileBtn);
        Button gameHistory = findViewById(R.id.gameHistoryBtn);

        //Verify if user is logged in using Google Account
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);

        //If logged in display Profile button
        if (googleSignInClient.silentSignIn().isComplete()){
            activateProfileActivityBtn();
            gameHistory.setVisibility(View.GONE);
        //If not logged in display Log In button and Game History button
        }else {
           //Change Profile button for Log In button
           profileButton.setImageResource(R.drawable.loginicon);
           profileButton.setBackgroundResource(0);
           profileButton.setOnClickListener(v -> {
               Intent startIntent = new Intent(MainActivity.this,LogInActivity.class);
               startActivity(startIntent);
           });
           gameHistory.setVisibility(View.GONE);
           gameHistory.setOnClickListener(v -> {
               Intent gameHistoryIntent = new Intent(MainActivity.this, GameHistoryActivity.class);
               startActivity(gameHistoryIntent);
           });
        }
        activateNewGameBtn();
        activateDiceRollerBtn();
        activateScoreTrackerBtn();
        activatePlayerSelectorBtn();


    }



    public void activateProfileActivityBtn(){
        ImageButton profileButton = findViewById(R.id.profileBtn);

        profileButton.setOnClickListener(v -> {
            Intent startIntent = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(startIntent);
        });

    }


    public void activateNewGameBtn(){
        Button newGameActivityBtn = findViewById(R.id.newGameBtn);

        newGameActivityBtn.setOnClickListener(v -> { //make an on click event
            Intent startIntent = new Intent(getApplicationContext(), NewGameActivity.class);//make new intent which launches the .xml file you want
            //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
            startActivity(startIntent);//start the activity
        });
    }

    public void activateDiceRollerBtn(){
        Button diceRollerActivityBtn = findViewById(R.id.diceRollerBtn);

        diceRollerActivityBtn.setOnClickListener(v -> { //make an on click event
            Intent startIntent = new Intent(getApplicationContext(), DiceRollerActivity.class);//make new intent which launches the .xml file you want
            //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
            startActivity(startIntent);//start the activity
        });
    }

    public void activateScoreTrackerBtn(){
        Button scoreTrackerActivityBtn = findViewById(R.id.scoreTrackerBtn);

        scoreTrackerActivityBtn.setOnClickListener(v -> { //make an on click event
            Intent startIntent = new Intent(getApplicationContext(), ScoreTrackerActivity.class);//make new intent which launches the .xml file you want
            //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
            startActivity(startIntent);//start the activity
        });
    }

    public void activatePlayerSelectorBtn(){
        Button playerSelectorActivityBtn = findViewById(R.id.playerSelectorBtn);

        playerSelectorActivityBtn.setOnClickListener(v -> { //make an on click event
            Intent startIntent = new Intent(getApplicationContext(), PlayerSelectorActivity.class);//make new intent which launches the .xml file you want
            //startIntent.putExtra("org.example.quickLauncher.SOMETHING", "Hello World!");//pass in extra info to new window
            startActivity(startIntent);//start the activity
        });
    }


}
