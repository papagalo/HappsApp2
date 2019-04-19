package com.example.happsapp2.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity (tableName = "band", indices = {@Index("bandID")})
public class Band implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int bandID;
    private String bandName;
    private String genreTags;

    public Band(String bandName, String genreTags) {
        this.bandName = bandName;
        this.genreTags = genreTags;
    }

    @Override
    public String toString() {
        return "Band{" +
                //"bandID=" + bandID +
                ", bandName='" + bandName + '\'' +
                ", genreTags='" + genreTags + '\'' +
                '}';
    }

    @NonNull
    public String getBandName() {
        return bandName;
    }

    public String getGenreTags() {
        return genreTags;
    }

    public int getBandID() { return bandID;  }

    public void setBandID(int bandID) { this.bandID = bandID; }

    protected Band(Parcel in) {
    }

    public static final Creator<Band> CREATOR = new Creator<Band>() {
        @Override
        public Band createFromParcel(Parcel in) {
            return new Band(in);
        }

        @Override
        public Band[] newArray(int size) {
            return new Band[size];
        }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
