package com.boardgamebuddy;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GameSessionViewModel extends AndroidViewModel{

    private GameSessionRepository mRepository;

    private LiveData<List<GameSession>> mAllSessions;

    private int totalPlayTime;
    private int totalSessions;
    private int totalWins;


    public GameSessionViewModel (Application application) {
        super(application);
        mRepository = new GameSessionRepository(application);
        mAllSessions = mRepository.getAllSessions();
        totalPlayTime = mRepository.getTotalPlayTime();
        totalSessions = mRepository.getTotalSessions();
        totalWins = mRepository.getTotalWins();
    }

    LiveData<List<GameSession>> getAllSessions() { return mAllSessions; }
    int getTotalPlayTime() { return totalPlayTime; }
    int getTotalSessions() { return totalSessions; }
    int getTotalWins() { return totalWins; }


    public void insert(GameSession gameSession) { mRepository.insert(gameSession); }
}
