package com.example.happsapp2.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

@Entity(indices = {@Index("band_ID"),
                   @Index("concert_ID"),
                   @Index("place_ID")},
        primaryKeys = {"user_ID"},
        foreignKeys = {@ForeignKey(entity = User.class,
                                  parentColumns = "userID",
                                  childColumns = "user_ID"),
                       @ForeignKey(entity = Band.class,
                                  parentColumns = "bandID",
                                  childColumns = "band_ID"),
                       @ForeignKey(entity = Concert.class,
                                   parentColumns = "concertID",
                                   childColumns = "concert_ID"),
                       @ForeignKey(entity = Places.class,
                                  parentColumns = "placeID",
                                  childColumns = "place_ID")}

)
public class Following implements Parcelable {


    private int user_ID;

    @Nullable
    private int band_ID;

    @Nullable
    private int concert_ID;

    @Nullable
    private int place_ID;

    protected Following(Parcel in) {
    }

    public static final Creator<Following> CREATOR = new Creator<Following>() {
        @Override
        public Following createFromParcel(Parcel in) {
            return new Following(in);
        }

        @Override
        public Following[] newArray(int size) {
            return new Following[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public Following(int user_ID,int band_ID, int concert_ID, int place_ID) {
        this.user_ID = user_ID;
        this.band_ID = band_ID;
        this.concert_ID = concert_ID;
        this.place_ID = place_ID;
    }

    @Override
    public String toString() {
        return "Following{" +
                "user_ID=" + user_ID +
                "band_ID=" + band_ID +
                ", concert_ID=" + concert_ID +
                ", place_ID=" + place_ID +
                '}';
    }

    public int getUser_ID() {
        return user_ID;
    }

    @Nullable
    public int getBand_ID() {
        return band_ID;
    }

    @Nullable
    public int getConcert_ID() {
        return concert_ID;
    }

    @Nullable
    public int getPlace_ID() {
        return place_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public void setBand_ID(int band_ID) {
        this.band_ID = band_ID;
    }

    public void setConcert_ID(int concert_ID) {
        this.concert_ID = concert_ID;
    }

    public void setPlace_ID(int place_ID) {
        this.place_ID = place_ID;
    }
}
