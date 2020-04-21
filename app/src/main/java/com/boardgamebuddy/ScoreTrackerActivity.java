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

    private Button plusBtn;
    private Button minusBtn;
    private TextView score;
    private Button plusBtn2;
    private Button minusBtn2;
    private TextView score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_tracker);

        score = findViewById(R.id.currentScoreText);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);

        score2 = findViewById(R.id.currentScoreText2);
        plusBtn2 = findViewById(R.id.plusBtn2);
        minusBtn2 = findViewById(R.id.minusBtn2);

        score.setText(Integer.toString(0));
        score2.setText(Integer.toString(0));

        plusBtnClick();
        minusBtnClick();
        plusBtnClick2();
        minusBtnClick2();

    }//end oncreate

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
    public void plusBtnClick2(){
        plusBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                int current = Integer.parseInt(score2.getText().toString());
                current++;
                //update display
                score2.setText("" + current);
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

    public void minusBtnClick2(){
        minusBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //make an on click event
                int current = Integer.parseInt(score2.getText().toString());
                current--;
                //update display
                score2.setText("" + current);
            }
        });

    }
}
