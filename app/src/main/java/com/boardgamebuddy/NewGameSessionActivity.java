package com.boardgamebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewGameSessionActivity extends AppCompatActivity {
    //*************FOR TESTING******* Functionality of this class will be superseded by game session UI - data will be initialised on end session and sent to Logic in GameHistoryActivity

    private EditText mProfileIDView;
    private EditText mGameView;
    private EditText mNumOfPlayers; //POTENTIAL ISSUES
    private EditText mScore;
    private Switch mWon;
    //private EditText mDate;
    private EditText mDuration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_session);
        mProfileIDView = findViewById(R.id.profileID);
        mGameView = findViewById(R.id.game);
        mNumOfPlayers = findViewById(R.id.numOfPlayers);
        mScore = findViewById(R.id.score);
        mWon = findViewById(R.id.won);
        //mDate = findViewById(R.id.date);
        mDuration = findViewById(R.id.duration);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mProfileIDView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {

                    String profile = mProfileIDView.getText().toString();
                    String game = mGameView.getText().toString();
                    int numOfPlayers = Integer.parseInt(mNumOfPlayers.getText().toString());
                    int score = Integer.parseInt(mScore.getText().toString());
                    boolean won;
                    if(mWon.isChecked()){//POTENTIAL ISSUE
                        won = true;
                    } else {
                        won = false;
                    }
                    int duration = Integer.parseInt(mDuration.getText().toString());

                    replyIntent.putExtra("profile", profile);
                    replyIntent.putExtra("game", game);
                    replyIntent.putExtra("numOfPlayers", numOfPlayers);
                    replyIntent.putExtra("score", score);
                    replyIntent.putExtra("won", won);
                    replyIntent.putExtra("duration", duration);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
