package com.example.happsapp2.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "userTable", indices = {@Index("userID")})
public class User implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int userID;

    @NonNull
    private String FName;

    @NonNull
    private String LName;

    @NonNull
    private String userName;

    private String password;

    public User(@NonNull String FName, @NonNull String LName,
                @NonNull String userName, @NonNull String password) {
        this.FName = FName;
        this.LName = LName;
        this.userName = userName;
        this.password = password;
    }

    protected User(Parcel in) {
        FName = in.readString();
        LName = in.readString();
        userName = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) { return new User(in);  }

        @Override
        public User[] newArray(int size) { return new User[size];  }
    };

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFName(@NonNull String FName) {
        this.FName = FName;
    }

    public void setLName(@NonNull String LName) {
        this.LName = LName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
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

    public String getPassword() { return password; }

    @NonNull
    public String getUserName() {
        return userName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(FName);
        dest.writeString(LName);
        dest.writeString(userName);
        dest.writeString(password);
    }
}
