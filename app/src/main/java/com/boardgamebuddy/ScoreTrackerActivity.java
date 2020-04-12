package com.boardgamebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreTrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_tracker);

//        Button enterBtn = (Button) findViewById(R.id.enterButton);
//        final EditText playerCount = (EditText) findViewById(R.id.playerCountEditText);
//        final LinearLayout scores = (LinearLayout) findViewById(R.id.scoresLinearLayout);
//
//
//        enterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int players;
//                scores.removeAllViews();
//                if (playerCount.getText().toString().isEmpty()){
//                    players = 0;
//                } else{
//                    players = Integer.parseInt(playerCount.getText().toString());
//                }
//
//                for (int i=0; i< players; i++){
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT);
//
//                    TextView playerName = new TextView(ScoreTrackerActivity.this);
//                    playerName.setText("Player "+ String.valueOf(i+1));
//                    playerName.setLayoutParams(params);
//                    playerName.setId(ViewCompat.generateViewId());
//                    playerName.setPadding(10,10,10,10);
//                    scores.addView(playerName);
//
//                    EditText playerScore = new EditText(ScoreTrackerActivity.this);
//                    playerScore.setLayoutParams(params);
//                    playerScore.setInputType(InputType.TYPE_CLASS_NUMBER);
//                    playerScore.setId(ViewCompat.generateViewId());
//                    playerScore.setPadding(10,10,10,10);
//                    scores.addView(playerScore);
//
//
//                }
//            }//end of onclick
//        });



    }//end
}
