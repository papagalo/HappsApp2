package com.example.happsapp2.persistence.UserDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.happsapp2.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM userTable")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM userTable WHERE userID = :user_id;")
    User getUserWithCustomQuery(int user_id);

    @Query("DELETE FROM usertable")
    void deleteAllUsers();

    @Delete
    int delete(User user);

    @Update
    int update(User user);

}
