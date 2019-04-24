package com.example.happsapp2.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "boardGames", indices = {@Index("boardGameID")})
public class BoardGame implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int boardGameID;

    @ColumnInfo(name = "board game name")
    private String bgName;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "startTime")
    private String startTime;

    @ColumnInfo(name = "endTime")
    private String endTime;

    public BoardGame(String bgName, String location,
                     String startTime, String endTime) {
        this.bgName = bgName;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        //this.boardGameID = boardGameID;
    }

    protected BoardGame(Parcel in) {
        bgName = in.readString();
        location = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        //boardGameID = in.readInt();
    }

    public static final Creator<BoardGame> CREATOR = new Creator<BoardGame>() {
        @Override
        public BoardGame createFromParcel(Parcel in) {
            return new BoardGame(in);
        }

        @Override
        public BoardGame[] newArray(int size) {
            return new BoardGame[size];
        }
    };

    public String getBgName() { return bgName; }

    public void setBgName(String bandName) {
        this.bgName = bandName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) { this.location = location; }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getBoardGameID() { return boardGameID;  }

    public void setBoardGameID(int boardGameID) { this.boardGameID = boardGameID;   }

    @Override
    public String toString() {
        return "BoardGame{" +
                "boardGameID='" + boardGameID + '\'' +
                ", bandName='" + bgName + '\'' +
                ", location='" + location + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bgName);
        dest.writeString(location);
        dest.writeString(startTime);
        dest.writeString(endTime);
        //dest.writeInt(boardGameID);
    }
}
