package com.example.happsapp2.models;

import android.arch.persistence.room.ColumnInfo;
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
    @ColumnInfo(name = "userName")
    private String userName;

    @ColumnInfo(name = "userPassword")
    private String userPassword;

    public User(@NonNull String userName, @NonNull String LName,
                @NonNull String FName, @NonNull String userPassword) {
        this.FName = FName;
        this.LName = LName;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    protected User(Parcel in) {
        FName = in.readString();
        LName = in.readString();
        userName = in.readString();
        userPassword = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) { return new User(in);  }

        @Override
        public User[] newArray(int size) { return new User[size];  }
    };

    public void setPassword(String password) {
        this.userPassword = password;
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

    public String getUserPassword() { return userPassword; }

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
        dest.writeString(userPassword);
    }
}
