package com.boardgamebuddy;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GameSessionRepository {

    private GameSessionDao mSessionDao;
    private LiveData<List<GameSession>> mAllSessions;
    private int totalPlayTime;
    private int totalSessions;
    private int totalWins;

    // Note that in order to unit test the Repository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    GameSessionRepository(Application application) {
        GameSessionRoomDatabase db = GameSessionRoomDatabase.getDatabase(application);
        mSessionDao = db.gameSessionDao();
        mAllSessions = mSessionDao.getOrderedGameSessions();
        totalPlayTime = mSessionDao.getTotalTimePlayed();
        totalSessions = mSessionDao.getTotalNumOfSessions();
        totalWins = mSessionDao.getNumOfWins();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<GameSession>> getAllSessions() {
        return mAllSessions;
    }

    int getTotalPlayTime() { return totalPlayTime; }
    int getTotalSessions() { return totalSessions; }
    int getTotalWins() { return totalWins; }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.

    void insert(GameSession gameSession) {
        GameSessionRoomDatabase.databaseWriteExecutor.execute(() -> {
            mSessionDao.insert(gameSession);
        });
    }

}
