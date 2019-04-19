package com.example.happsapp2.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = @Index("userID"))
public class User {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int userID;

    @NonNull
    private String FName;

    @NonNull
    private String LName;

    @NonNull
    private String handle;



    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFName(@NonNull String FName) {
        this.FName = FName;
    }

    public void setLName(@NonNull String LName) {
        this.LName = LName;
    }

    public void setHandle(@NonNull String handle) {
        this.handle = handle;
    }

    public int getUserID() {
        return userID;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    @NonNull
    public String getHandle() {
        return handle;
    }

    public User(int userID, @NonNull String FName, @NonNull String LName, @NonNull String handle) {
        this.userID = userID;
        this.FName = FName;
        this.LName = LName;
        this.handle = handle;
    }


}
