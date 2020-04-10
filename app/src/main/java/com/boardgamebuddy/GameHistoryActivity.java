package com.boardgamebuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class GameHistoryActivity extends AppCompatActivity {

    private GameSessionViewModel mSessionViewModel;
    public static final int NEW_SESSION_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);
        RecyclerView recyclerView = findViewById(R.id.recyclerview); //SAME LAYOUT REFERENCE HERE
        final GameSessionListAdapter adapter = new GameSessionListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSessionViewModel = new ViewModelProvider(this).get(GameSessionViewModel.class);
        mSessionViewModel.getAllSessions().observe(this, new Observer<List<GameSession>>() {
            @Override
            public void onChanged(@Nullable final List<GameSession> sessions) {
                // Update the cached copy of the sessions in the adapter.
                adapter.setSessions(sessions);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameHistoryActivity.this, NewGameSessionActivity.class);
                startActivityForResult(intent, NEW_SESSION_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_SESSION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Date currentDate = new Date();

            GameSession newSession = new GameSession(data.getStringExtra("profile"), data.getStringExtra("game"), data.getIntExtra("numOfPlayers", 0), data.getIntExtra("score", 0), data.getBooleanExtra("won", false), currentDate, data.getIntExtra("duration", 0));

            mSessionViewModel.insert(newSession);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
