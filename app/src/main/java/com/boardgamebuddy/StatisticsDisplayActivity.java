package com.boardgamebuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class StatisticsDisplayActivity extends AppCompatActivity {

    private GameSessionViewModel mSessionViewModel;
    public static final int NEW_SESSION_ACTIVITY_REQUEST_CODE = 1;

    private int totalPlayTime;
    private int totalSessions;
    private int totalWins;

    private TextView totalTimeView;
    private TextView totalSessionsView;
    private TextView winRatioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_display);
        //RecyclerView recyclerView = findViewById(R.id.recyclerview); //SAME LAYOUT REFERENCE HERE
        //final GameSessionListAdapter adapter = new GameSessionListAdapter(this);
        //recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSessionViewModel = new ViewModelProvider(this).get(GameSessionViewModel.class);


        totalPlayTime = mSessionViewModel.getTotalPlayTime();
        totalSessions = mSessionViewModel.getTotalSessions();
        totalWins = mSessionViewModel.getTotalWins();

        totalTimeView = findViewById(R.id.totalTimeTextView);
        totalSessionsView = findViewById(R.id.totalSessionsTextView);
        winRatioView = findViewById(R.id.winPercentageTextView);

        /*mSessionViewModel.getAllSessions().observe(this, new Observer<List<GameSession>>() {
            @Override
            public void onChanged(@Nullable final List<GameSession> sessions) {
                // Update the cached copy of the sessions in the adapter.
                adapter.setSessions(sessions);
            }
        });*/

        int winRatio = ((totalWins * 100) / totalSessions );

        totalTimeView.setText("" + totalPlayTime + " minutes");
        totalSessionsView.setText("" + totalSessions);
        winRatioView.setText("" + winRatio + "%");

    }
}
