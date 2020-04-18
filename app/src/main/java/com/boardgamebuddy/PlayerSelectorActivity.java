package com.boardgamebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class PlayerSelectorActivity extends AppCompatActivity {

    int numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selector);


        SeekBar numPlayersSeekBar = (SeekBar) findViewById(R.id.playersSeekBar);
        TextView playersTextView = (TextView) findViewById(R.id.playersTextView);
        Button firstPlayerButton = (Button) findViewById(R.id.firstPlayerButton);
        TextView firstPlayerTextView = (TextView) findViewById(R.id.firstPlayerTextView);
        //int numPlayers;

        numPlayersSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue =0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress+2;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               playersTextView.setText(String.valueOf(progressChangedValue));
               numPlayers = progressChangedValue;
            }
        });//end seek bar changer

        firstPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int choice = rand.nextInt(numPlayers) + 1;
                String firstPlayerMessage ="Player " + choice +"!";

                firstPlayerTextView.setText(String.valueOf(firstPlayerMessage));
            }
        });

    }
}
