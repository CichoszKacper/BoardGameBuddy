package com.boardgamebuddy;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {GameSession.class}, version = 1, exportSchema = false) //CHANGE THIS TO EXPORT SCHEMA TO A DIRECTORY TO CHECK
@TypeConverters({Converters.class})
public abstract class GameSessionRoomDatabase extends RoomDatabase {

    public abstract GameSessionDao gameSessionDao();

    private static volatile GameSessionRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static GameSessionRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GameSessionRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GameSessionRoomDatabase.class, "game_session_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                GameSessionDao dao = INSTANCE.gameSessionDao();
                dao.deleteAll();

                //String profileID, String game, int numOfPlayers, int score, boolean won, Date date, int duration
                Date testDate = new Date(2020, 02, 01);

                Date date = new Date();
                //Date testDate2 = new Date(2019, 03, 03);



                GameSession session = new GameSession("TestMember", "Gametitle1", 3, 10, true, date, 25);
                dao.insert(session);

                //GameSession session2 = new GameSession("TestMember", "Gametitle2", 3, 20, false, testDate2, 35);
                //dao.insert(session2);
            });
        }
    };

        /*  CODE TO DROP AND RECREATE DATABASE ON START UP
     static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback).build();
}
            }
                    }
                    return INSTANCE;
                    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                Word word = new Word("Hello");
                dao.insert(word);
                word = new Word("World");
                dao.insert(word);
            });
        }
    };*/
}
