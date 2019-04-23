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

    @ColumnInfo(name = "band name")
    private String bandName;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "startTime")
    private String startTime;

    @ColumnInfo(name = "endTime")
    private String endTime;

    @ColumnInfo(name = "genre")
    private String genre;

    public Concert(String bandName,String genre, String location,
                   String startTime, String endTime) {
        this.bandName = bandName;
        this.genre = genre;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        //this.concertID = concertID;
    }

    protected Concert(Parcel in) {
        bandName = in.readString();
        genre = in.readString();
        location = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        //concertID = in.readInt();
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

    public String getBandName() { return bandName; }

    public void setBandName(String bandName) {
        this.bandName = bandName;
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
                "concertID='" + concertID + '\'' +
                ", bandName='" + bandName + '\'' +
                ", genre='" + genre + '\'' +
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
        dest.writeString(bandName);
        dest.writeString(genre);
        dest.writeString(location);
        dest.writeString(startTime);
        dest.writeString(endTime);
        //dest.writeInt(concertID);
    }
}
