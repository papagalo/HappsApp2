package com.example.happsapp2.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class Places implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int placeID;

    private String placeName;

    @NonNull
    private String address;

    @NonNull
    private String placeCategory;

    public Places(int placeID, String placeName, @NonNull String address, @NonNull String placeCategory) {
        this.placeID = placeID;
        this.placeName = placeName;
        this.address = address;
        this.placeCategory = placeCategory;
    }

    @NonNull
    public String getPlaceName() {
        return placeName;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    @NonNull
    public String getPlaceCategory() {
        return placeCategory;
    }

    @NonNull
    public int getPlaceID() { return placeID; }

    protected Places(Parcel in) {
    }

    public static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public String toString() {
        return "Places{" +
                "placeID=" + placeID +
                ", placeName='" + placeName + '\'' +
                ", address='" + address + '\'' +
                ", placeCategory='" + placeCategory + '\'' +
                '}';
    }
}
