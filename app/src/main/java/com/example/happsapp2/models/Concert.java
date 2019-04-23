package com.example.happsapp2.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "concerts", indices = {@Index("concertID")})
public class Concert implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int concertID;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "startTime")
    private String startTime;

    @ColumnInfo(name = "endTime")
    private String endTime;

    @ColumnInfo(name = "genre")
    private String genre;

    public Concert(String title, String location, String startTime,
                   String endTime, String genre) {
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.genre = genre;
        //this.concertID = concertID;
    }

    protected Concert(Parcel in) {
        title = in.readString();
        location = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        concertID = in.readInt();
        genre = in.readString();
    }

    public static final Creator<Concert> CREATOR = new Creator<Concert>() {
        @Override
        public Concert createFromParcel(Parcel in) {
            return new Concert(in);
        }

        @Override
        public Concert[] newArray(int size) {
            return new Concert[size];
        }
    };

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
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

    public int getConcertID() { return concertID;  }

    public void setConcertID(int concertID) { this.concertID = concertID;   }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String toString() {
        return "Concert{" +
                //"concertID='" + concertID + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(location);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeInt(concertID);
        dest.writeString(genre);
    }
}
