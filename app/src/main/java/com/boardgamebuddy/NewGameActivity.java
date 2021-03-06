package com.boardgamebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class NewGameActivity extends AppCompatActivity {
    private EditText numOfPlayers;
    private EditText gameTitle;
    private EditText score;
    private boolean timerSelected = false;
    private EditText minutesText;
    private EditText secondsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        minutesText = findViewById(R.id.minutesEditText);
        secondsText = findViewById(R.id.secondsEditText);
        final TextView timerColon = (TextView) findViewById(R.id.timerColon);
        numOfPlayers = findViewById(R.id.playersEditText);
        gameTitle = findViewById(R.id.titleEditText);
        score = findViewById(R.id.scoreEditText);

        minutesText.setVisibility(EditText.GONE);
        timerColon.setVisibility(EditText.GONE);
        secondsText.setVisibility(EditText.GONE);

        Switch timerSwitch = (Switch) findViewById(R.id.timerSwitch);
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    timerSelected = true;
                    minutesText.setVisibility(EditText.VISIBLE);
                    timerColon.setVisibility(EditText.VISIBLE);
                    secondsText.setVisibility(EditText.VISIBLE);
                } else {
                    minutesText.setVisibility(EditText.GONE);
                    timerColon.setVisibility(EditText.GONE);
                    secondsText.setVisibility(EditText.GONE);
                }
            }
        });

        activateStartButton();

    }

    public void activateStartButton(){
        Button startBtn = findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                Intent startIntent = new Intent(getApplicationContext(), GameSessionActivity.class);//make new intent which launches the .xml file you want
                int playerCount = -1; //default value for no input
                String title = "Undefined";
                int startingScore = 0;
                if(TextUtils.isEmpty(numOfPlayers.getText())){
                    //don't change value
                } else{
                    playerCount = Integer.parseInt(numOfPlayers.getText().toString());
                }

                if(TextUtils.isEmpty(gameTitle.getText())){
                    //don't change value
                } else{
                    title = gameTitle.getText().toString();
                }


                int minutes = 0;
                int seconds = 0;
                int total = 0;
                if(timerSelected){
                    //get timer details
                    if(TextUtils.isEmpty(minutesText.getText()) ){
                        //do nothing
                    } else{
                        minutes = Integer.parseInt(minutesText.getText().toString());
                    }

                    if(TextUtils.isEmpty(secondsText.getText())) {
                        //do nothing
                    } else {
                        seconds = Integer.parseInt(secondsText.getText().toString());
                    }


                    total = (minutes * 60) + seconds;

                }

                if(TextUtils.isEmpty(score.getText())){
                    //don't change value
                } else{
                    startingScore = Integer.parseInt(score.getText().toString());
                }

                startIntent.putExtra("timerSeconds", total);//pass in extra info to new window
                startIntent.putExtra("timerSelected", timerSelected);
                startIntent.putExtra("playerCount", playerCount);//pass in extra info to new window
                startIntent.putExtra("title", title);//pass in extra info to new window
                startIntent.putExtra("score", startingScore);//pass in extra info to new window
                startActivity(startIntent);//start the activity
            }
        });
    }


}