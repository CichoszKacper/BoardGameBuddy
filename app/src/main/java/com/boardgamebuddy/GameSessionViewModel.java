package com.boardgamebuddy;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GameSessionViewModel extends AndroidViewModel{

    private GameSessionRepository mRepository;

    private LiveData<List<GameSession>> mAllSessions;

    public GameSessionViewModel (Application application) {
        super(application);
        mRepository = new GameSessionRepository(application);
        mAllSessions = mRepository.getAllSessions();
    }

    LiveData<List<GameSession>> getAllSessions() { return mAllSessions; }

    public void insert(GameSession gameSession) { mRepository.insert(gameSession); }
}
