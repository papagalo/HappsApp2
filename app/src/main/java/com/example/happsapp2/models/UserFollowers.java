package com.example.happsapp2.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(indices = {@Index("follower_ID"),
                   @Index("user_ID")},
        primaryKeys = {"user_ID","follower_ID"},
        foreignKeys = {
            @ForeignKey(entity = User.class,
                        parentColumns = "userID",
                        childColumns = "user_ID"),
            @ForeignKey(entity = User.class,
                        parentColumns = "userID",
                        childColumns = "follower_ID")})
public class UserFollowers implements Parcelable {

    private int user_ID;
    private int follower_ID;

    public UserFollowers(int user_ID, int follower_ID) {
        this.user_ID = user_ID;
        this.follower_ID = follower_ID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public int getFollower_ID() {
        return follower_ID;
    }

    @Ignore
    public UserFollowers(){}

    protected UserFollowers(Parcel in) {
    }

    public static final Creator<UserFollowers> CREATOR = new Creator<UserFollowers>() {
        @Override
        public UserFollowers createFromParcel(Parcel in) {
            return new UserFollowers(in);
        }

        @Override
        public UserFollowers[] newArray(int size) {
            return new UserFollowers[size];
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
        return "UserFollowers{" +
                "user_ID=" + user_ID +
                ", follower_ID=" + follower_ID +
                '}';
    }
}
