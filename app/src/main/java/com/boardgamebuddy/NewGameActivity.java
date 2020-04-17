package com.boardgamebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class NewGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        final EditText minutes = (EditText) findViewById(R.id.minutesEditText);
        final EditText seconds = (EditText) findViewById(R.id.secondsEditText);
        final TextView timerColon = (TextView) findViewById(R.id.timerColon);

        minutes.setVisibility(EditText.GONE);
        timerColon.setVisibility(EditText.GONE);
        seconds.setVisibility(EditText.GONE);

        Switch timerSwitch = (Switch) findViewById(R.id.timerSwitch);
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    minutes.setVisibility(EditText.VISIBLE);
                    timerColon.setVisibility(EditText.VISIBLE);
                    seconds.setVisibility(EditText.VISIBLE);
                } else {
                    minutes.setVisibility(EditText.GONE);
                    timerColon.setVisibility(EditText.GONE);
                    seconds.setVisibility(EditText.GONE);
                }
            }
        });
    }


}