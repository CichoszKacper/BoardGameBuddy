package com.boardgamebuddy;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = "game_session")
public class GameSession implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sessionID")
    public int id;

    @ColumnInfo(name = "profileID")
    public String ProfileID;

    @ColumnInfo(name = "game")
    public  String Game;

    @ColumnInfo(name = "num_of_players")
    public  int NumOfPlayers;

    @ColumnInfo(name = "score")
    public  int Score;

    @ColumnInfo(name = "won")
    public  boolean Won;

    @ColumnInfo(name = "date")
    public Date Date;

    @ColumnInfo(name = "duration")
    public  int Duration;

    public GameSession(String ProfileID, String Game, int NumOfPlayers, int Score, boolean Won, Date Date, int Duration) {
        this.ProfileID = ProfileID;
        this.Game = Game;
        this.NumOfPlayers = NumOfPlayers;
        this.Score = Score;
        this.Won = Won;
        this.Date = Date;
        this.Duration = Duration;
    }


    public String getSession(){
        String output = "";
        //output += "id (for testing) " + this.id + "\n";
        //output += "ProfileID (for testing) " + this.ProfileID + "\n";
        //default values are
        //-1 for total players
        //"Undefined" for game title
        //Boolean for win/loss



        output += "Date: " + this.Date + "\n";
        if(this.Game.equalsIgnoreCase("Undefined")){
            output += "Game: " + "Unspecified" + "\n";
        } else{
            output += "Game: " + this.Game + "\n";
        }

        if(this.Won){
            output += "Outcome: " + "Won!" + "\n";
        } else{
            output += "Outcome: " + "Lost" + "\n";
        }

        output += "Score: " + this.Score + "\n";
        if(this.NumOfPlayers == -1){
            output += "Total players: " + "Unspecified" + "\n";
        }
        output += "Duration in minutes: " + this.Duration;
        return output;
    }




}
