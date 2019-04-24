package com.example.happsapp2.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "videoGames", indices = {@Index("videoGameID")})
public class VideoGame implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int videoGameID;

    @ColumnInfo(name = "video game name")
    private String vgName;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "startTime")
    private String startTime;

    @ColumnInfo(name = "endTime")
    private String endTime;

    @ColumnInfo(name = "genre")
    private String genre;

    public VideoGame(String vgName, String genre, String location,
                     String startTime, String endTime) {
        this.vgName = vgName;
        this.genre = genre;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        //this.videoGameID = videoGameID;
    }

    protected VideoGame(Parcel in) {
        vgName = in.readString();
        genre = in.readString();
        location = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        //videoGameID = in.readInt();
    }

    public static final Creator<VideoGame> CREATOR = new Creator<VideoGame>() {
        @Override
        public VideoGame createFromParcel(Parcel in) {
            return new VideoGame(in);
        }

        @Override
        public VideoGame[] newArray(int size) {
            return new VideoGame[size];
        }
    };

    public String getVgName() { return vgName; }

    public void setVgName(String vgName) {
        this.vgName = vgName;
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

    public int getVideoGameID() { return videoGameID;  }

    public void setVideoGameID(int videoGameID) { this.videoGameID = videoGameID;   }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String toString() {
        return "VideoGame{" +
                "videoGameID='" + videoGameID + '\'' +
                ", bandName='" + vgName + '\'' +
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
        dest.writeString(vgName);
        dest.writeString(genre);
        dest.writeString(location);
        dest.writeString(startTime);
        dest.writeString(endTime);
        //dest.writeInt(videoGameID);
    }
}
