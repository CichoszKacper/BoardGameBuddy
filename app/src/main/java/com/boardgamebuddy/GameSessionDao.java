package com.boardgamebuddy;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameSessionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GameSession gameSession);

    @Query("DELETE FROM game_session")
    void deleteAll();

    @Query("SELECT * from game_session ORDER BY date DESC")
    LiveData<List<GameSession>> getOrderedGameSessions();

    @Query("SELECT SUM(duration) from game_session")
    int getTotalTimePlayed();

    @Query("SELECT COUNT(*) from game_session")
    int getTotalNumOfSessions();

    @Query("SELECT COUNT(*) from game_session WHERE won = 1")
    int getNumOfWins();


}
